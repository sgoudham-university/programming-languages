//////////////////////////////////////////////////////////////
//
// Representation and interpretation of FunVM code.
//
// Based on a previous version developed by
// David Watt and Simon Gay (University of Glasgow).
//
//////////////////////////////////////////////////////////////

package fun;

import java.io.*;
import java.util.*;

public class SVM {

	// Each SVM object is a simple virtual machine. 
	// This comprises a code store, a data store, and 
	// registers pc (program counter), sp (stack pointer), 
	// fp (frame pointer), and status (initially RUNNING).

	// The data store contains a stack of words. Register sp 
	// points to the first free word above the stack top.

	// The code store contains byte-codes.
	// Each instruction occupies 1-3 bytes, in which the 
	// first byte contains the opcode.
	// Register pc points to the first byte of the next 
	// instruction to be executed. 
	// The instruction set is as follows:
	//
	// Opcode Bytes Mnemonic	Behaviour
	//    0    1+2  LOADG d    w <- word at global address d;
	//                         push w.
	//    1    1+2  STOREG d   pop w;
	//                         word at global address d <- w.
	//    2    1+2  LOADL d    w <- word at local address d;
	//                         push w.
	//    3    1+2  STOREL d   pop w;
	//                         word at local address d <- w.
	//    4    1+2  LOADC w    push w.
	//    6    1    ADD        pop w2; pop w1; push (w1+w2).
	//    7    1    SUB        pop w2; pop w1; push (w1-w2).
	//    8    1    MUL        pop w2; pop w1; push (w1*w2).
	//    9    1    DIV        pop w2; pop w1; push (w1/w2).
	//   10    1    CMPEQ      pop w2; pop w1;
	//                         push (if w1=w2 then 1 else 0).
	//   12    1    CMPLT      pop w2; pop w1;
	//                         push (if w1<w2 then 1 else 0).
	//   13    1    CMPGT      pop w2; pop w1;
	//                         push (if w1>w2 then 1 else 0).
	//   14    1    INV        pop w;
	//                         push (if w==0 then 1 else 0).
	//   15    1    INC        pop w; push w+1.
	//   16    1    HALT       status <- HALTED.
	//   17    1+2  JUMP c     pc <- c.
	//   18    1+2  JUMPF c    pop w; if w=0 then pc <- c.
	//   19    1+2  JUMPT c    pop w; if w!=0 then pc <- c.
	//   20    1+2  CALL c     push a new frame initially 
	//                         containing only
	//                         dynamic link <- fp and
	//                         return address <- pc;
	//                         fp <- base address of frame;
	//                         pc <- c.
	//   21    1+1  RETURN r   pop the result (r words); 
	//                         pop topmost frame;
	//                         push the result (r words);
	//                         fp <- dynamic link;
	//                         pc <- return address.
	//   22    1+1  COPYARG s  swap arguments (s words) into
	//                         the topmost frame, just above 
	//                         the return address.

	public static final byte        // opcodes
	   LOADG   =  0,  STOREG  =  1,
	   LOADL   =  2,  STOREL  =  3,
	   LOADC   =  4,
	   ADD     =  6,  SUB     =  7,
	   MUL     =  8,  DIV     =  9,
	   CMPEQ   = 10,
	   CMPLT   = 12,  CMPGT   = 13,
	   INV     = 14,  INC     = 15,
	   HALT    = 16,  JUMP    = 17,
	   JUMPF   = 18,  JUMPT   = 19,
	   CALL    = 20,  RETURN  = 21,
	   COPYARG = 22;

	private static final String[] mnemonic = {
	   "LOADG   ",    "STOREG  ",
	   "LOADL   ",    "STOREL  ",
	   "LOADC   ",    "???     ",
	   "ADD     ",    "SUB     ",
	   "MUL     ",    "DIV     ",
	   "CMPEQ   ",    "???     ",
	   "CMPLT   ",    "CMPGT   ",
	   "INV     ",    "INC     ",
	   "HALT    ",    "JUMP    ",
	   "JUMPF   ",    "JUMPT   ",
	   "CALL    ",    "RETURN  ",
	   "COPYARG "                };

	private static final int[] bytes = {
	   3,             3,
	   3,             3,
	   3,             1,
	   1,             1,
	   1,             1,
	   1,             1,
	   1,             1,
	   1,             1,
	   1,             3,
	   3,             3,
	   3,             2,
	   2                };

	public static final byte        // status codes
	   RUNNING = 0,
	   HALTED  = 1,
	   FAILED  = 2;

	public static final int         // offsets of IO routines
	   READOFFSET    = 32766,
	   WRITEOFFSET   = 32767,
	   IOBASE        = 32766;


	// MACHINE STATE

	protected byte[] code;     // code store
	protected int cl;          // code limit
	protected int pc;          // program counter

	protected int[] data;      // data store (stack)
	protected int sp;          // stack pointer
	protected int fp;          // frame pointer

	protected byte status;


	// CONSTRUCTOR

	public SVM () {
		code = new byte[32768];
		cl = 0;
	}


	// CODE INTERPRETATION

	private static Scanner in = new Scanner(System.in);
	private static PrintStream out = System.out;

	public void interpret (boolean tracing) {
	// Interpret the program starting at offset 0 
	// in the code store.
	// If tracing is true, print each instruction 
	// as it is executed.
		data = new int[32768];
		pc = 0;
		sp = 0;
		fp = 0;
		status = RUNNING;
		do {
			if (tracing)
				out.println(showInstruction(pc));
			byte opcode = code[pc++];
			switch (opcode) {
				case LOADG: {
					int d =  // addr of global variable
					   code[pc++]<<8 |
					   (code[pc++]&0xFF);
					data[sp++] = data[d];
					break;
				}
				case STOREG: {
					int d =  // addr of global variable
					   code[pc++]<<8 |
					   (code[pc++]&0xFF);
					data[d] = data[--sp];
					break;
				}
				case LOADL: {
					int d =  // addr of local variable
					   code[pc++]<<8 |
					   (code[pc++]&0xFF);
					data[sp++] = data[fp+d];
					break;
				}
				case STOREL: {
					int d =  // addr of local variable
					   code[pc++]<<8 |
					   (code[pc++]&0xFF);
					data[fp+d] = data[--sp];
					break;
				}
				case LOADC: {
					int w =  // value of literal
					   code[pc++]<<8 |
					   (code[pc++]&0xFF);
					data[sp++] = w;
					break;
				}
				case ADD: {
					int w2 = data[--sp];
					int w1 = data[--sp];
					data[sp++] = w1 + w2;
					break;
				}
				case SUB: {
					int w2 = data[--sp];
					int w1 = data[--sp];
					data[sp++] = w1 - w2;
					break;
				}
				case MUL: {
					int w2 = data[--sp];
					int w1 = data[--sp];
					data[sp++] = w1 * w2;
					break;
				}
				case DIV: {
					int w2 = data[--sp];
					int w1 = data[--sp];
					data[sp++] = w1 / w2;
					break;
				}
				case CMPEQ: {
					int w2 = data[--sp];
					int w1 = data[--sp];
					data[sp++] = (w1 == w2 ? 1 : 0);
					break;
				}
				case CMPLT: {
					int w2 = data[--sp];
					int w1 = data[--sp];
					data[sp++] = (w1 < w2 ? 1 : 0);
					break;
				}
				case CMPGT: {
					int w2 = data[--sp];
					int w1 = data[--sp];
					data[sp++] = (w1 > w2 ? 1 : 0);
					break;
				}
				case INV: {
					int w = data[--sp];
					data[sp++] = (w == 0 ? 1 : 0);
					break;
				}
				case INC: {
					int w = data[--sp];
					data[sp++] = w + 1;
					break;
				}
				case HALT: {
					status = HALTED;
					break;
				}
				case JUMP: {
					int c =  // target of jump
					   code[pc++]<<8 |
					   (code[pc++]&0xFF);
					pc = c;
					break;
				}
				case JUMPF: {
					int c =  // target of jump
					   code[pc++]<<8 |
					   (code[pc++]&0xFF);
					int w = data[--sp];
					if (w == 0)
						pc = c;
					break;
				}
				case JUMPT: {
					int c =  // target of jump
					   code[pc++]<<8 |
					   (code[pc++]&0xFF);
					int w = data[--sp];
					if (w != 0)
						pc = c;
					break;
				}
				case CALL: {
					int c =  // address of callee
					   code[pc++]<<8 |
					   (code[pc++]&0xFF);
					if (c >= IOBASE) {
						callIO(c);
						break;
					}
					data[sp++] = fp;  // dyn link
					data[sp++] = pc;  // return addr
					fp = sp-2;
					pc = c;
					break;
				}

				case RETURN: {
					int r = code[pc++];  // result size
					int dl = data[fp];   // dyn link
					int ra = data[fp+1]; // return addr
					// Shift result down to top of 
					// caller's frame:
					for (int i = 0; i < r; i++)
						data[fp+i] = data[sp-r+i];
					sp = fp+r;
					fp = dl;
					pc = ra;
					break;
				}
				case COPYARG: {
					int s = code[pc++];  // args size
					int dl = data[fp];   // dyn link
					int ra = data[fp+1]; // return addr
					// Shift arguments up by 2 words, 
					// to make room for link data:
					for (int i = 0; i < s; i++)
						data[fp-i+1] = data[fp-i-1];
					// Move link data under arguments:
					fp -= s; 
					data[fp] = dl;
					data[fp+1] = ra;
					break;
				}
				default: {
					out.println("Illegal instruction"
					   + opcode);
					status = FAILED;
				}
			}
		} while (status == RUNNING);
	}

	private void callIO (int c) {
	// Execute a call to an IO routine.
		switch (c) {
			case READOFFSET: {
				out.print("? ");
				int w = in.nextInt();
				data[sp++] = w;
				break;
			}
			case WRITEOFFSET: {
				int w = data[--sp];
				out.println(w);
				break;
			}
		}
	}


	// CODE DISPLAY

	public String showCode () {
	// Return a textual representation of all the code.
		String assembly = "";
		for (int c = 0; c < cl;) {
			assembly += showInstruction(c) + "\n";
			c += bytes[code[c]];
		}
		return assembly;
	}

	private String showInstruction (int c) {
	// Return a textual representation of the instruction 
	// at offset c in the code store.
		byte opcode = code[c++];
		String line =
		   String.format("%6d: %s", c-1, mnemonic[opcode]);
		switch (bytes[opcode]) {
			case 1:
				break;
			case 2: {
				byte operand = code[c++];
				line += operand;
				break;
			}
			case 3: {
				int operand =
				   code[c++]<<8 | (code[c++]&0xFF);
				line += operand;
				break;
			}
		}
		return line;
	}


	// STACK DISPLAY

	private String showStack () {
	// Return a textual representation of the stack contents.
		String show = "";
		int dl = fp;
		for (int a = sp-1; a >=0; a--) {
			show +=
			   String.format("%6d: %6d\n", a, data[a]);
			if (a == dl) {
				show += "        ------\n";
				dl = data[a];
			}
		}
		return show;
	}


	// CODE EMISSION

	public void emit1 (byte opcode) {
	// Add a 1 byte instruction to the code.
		code[cl++] = opcode;
	}

	public void emit11 (byte opcode,
	                     int operand) {
	// Add a 1+1 byte instruction to the code.
		code[cl++] = opcode;
		code[cl++] = (byte) operand;
	}

	public void emit12 (byte opcode,
	                    int operand) {
	// Add a 1+2 byte instruction to the code.
		code[cl++] = opcode;
		code[cl++] = (byte) (operand >> 8);
		code[cl++] = (byte) (operand & 0xFF);
	}

	public void patch12 (int addr, int operand) {
	// Patch an operand into a 1+2 byte instruction.
		code[addr+1] = (byte) (operand >> 8);
		code[addr+2] = (byte) (operand & 0xFF);
	}

	public int currentOffset () {
	// Return the offset of the next instruction to be added.
		return cl;
	}

}