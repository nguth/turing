package program.loader;

import program.Program;

/**
 *
 */
public interface ProgramLoader {
    /* Load a program and return a Program instance  */
    Program load(String program);
}
