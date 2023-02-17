# Programming Languages 

# Lecture One

## Intro

### What _should_ A Programming Language Be?

- Should be universal, capable of expressing any computation
- Natural for expressing computations in its indended area
- Implementable
- Reasonably **efficient** (changes from context to context, time to time)

## Syntax

- Concernd with the _form_ of programs, how expressions and commands declarations and other constructs are arranged to make a well-formed program
- It _must_ be defined for any PL, formal **or** informal

Informal: Expressed in a natural language  
Formal: Expressed in a precise notation

Both are still the same level of precision (usually...) 

- Regex: good for specifying syntax of lexical elements of programs
- BNF: Good for specifying syntax of larger and nested program constructs
- EBNF: Nearly good for everything

## Grammar

The grammer of a language is a set of rules specifying how the phrases of that language are formed.


# Lecture Two

## Types

languages have types :woohoo:

- Values, grouped into types based on what operations can be performed on them
- Type, a set of values, equipped with operations that can be applied uniformly to all values

e.g. BOOL: {false, true} and comes with logical not, and, or.

- Cardinality, the number of values the type has

e.g. #BOOL = 2

Expression `E` is of type `T` if, when evaluation of E _terminates_ 
normally and guaranteed to yield a value v of type t

## Primitive Types

Primitive types are types whose values are primitive/atomic (can't be broken down further)

Some languages allow extensions of the default built-in primitive types, defined by the user

Definitions of primitive types vary from language to language. E.g. C VS Java

## Composite Types

Composite Types are the inverse of primitive types, they can be further decomposed/broken 
down into further parts

### Cartesian Products

E.g tuples/structs/records

A set of values that are in pairs/tuples

### Disjoint Unions

A value is chosen from one of two (or more) different types

### Mappings

### Recursive Types

## Type Systems

## Expressions
