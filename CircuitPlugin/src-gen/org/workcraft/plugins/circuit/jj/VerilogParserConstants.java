/* Generated By:JavaCC: Do not edit this line. VerilogParserConstants.java */
package org.workcraft.plugins.circuit.jj;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface VerilogParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int MODULE = 13;
  /** RegularExpression Id. */
  int ENDMODULE = 14;
  /** RegularExpression Id. */
  int INPUT = 15;
  /** RegularExpression Id. */
  int OUTPUT = 16;
  /** RegularExpression Id. */
  int INOUT = 17;
  /** RegularExpression Id. */
  int REG = 18;
  /** RegularExpression Id. */
  int WIRE = 19;
  /** RegularExpression Id. */
  int ASSIGN = 20;
  /** RegularExpression Id. */
  int NAME = 21;
  /** RegularExpression Id. */
  int FORMULA = 22;
  /** RegularExpression Id. */
  int PETRIFY_NAME = 23;
  /** RegularExpression Id. */
  int STRING = 24;
  /** RegularExpression Id. */
  int CHAR = 25;
  /** RegularExpression Id. */
  int ESCAPESEQ = 26;
  /** RegularExpression Id. */
  int LOGIC0 = 27;
  /** RegularExpression Id. */
  int LOGIC1 = 28;
  /** RegularExpression Id. */
  int INTEGER = 29;
  /** RegularExpression Id. */
  int PETRIFY_EQUATION = 30;
  /** RegularExpression Id. */
  int PETRIFY_UNMAPPED = 31;
  /** RegularExpression Id. */
  int PETRIFY_ZERO_DELAY = 32;
  /** RegularExpression Id. */
  int MPSAT_ZERO_DELAY = 33;
  /** RegularExpression Id. */
  int PETRIFY_INIT_STATE = 34;
  /** RegularExpression Id. */
  int MPSAT_INIT_STATE = 35;
  /** RegularExpression Id. */
  int WS = 36;
  /** RegularExpression Id. */
  int NL = 37;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int WITHIN_SPECIFY = 1;
  /** Lexical state. */
  int WITHIN_PRIMITIVE = 2;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\r\"",
    "\"\\n\"",
    "<token of kind 5>",
    "<token of kind 6>",
    "\"specify\"",
    "\"primitive\"",
    "\"endspecify\"",
    "<token of kind 10>",
    "\"endprimitive\"",
    "<token of kind 12>",
    "\"module\"",
    "\"endmodule\"",
    "\"input\"",
    "\"output\"",
    "\"inout\"",
    "\"reg\"",
    "\"wire\"",
    "\"assign\"",
    "<NAME>",
    "<FORMULA>",
    "<PETRIFY_NAME>",
    "<STRING>",
    "<CHAR>",
    "<ESCAPESEQ>",
    "\"1\\\'b0\"",
    "\"1\\\'b1\"",
    "<INTEGER>",
    "<PETRIFY_EQUATION>",
    "<PETRIFY_UNMAPPED>",
    "<PETRIFY_ZERO_DELAY>",
    "<MPSAT_ZERO_DELAY>",
    "<PETRIFY_INIT_STATE>",
    "<MPSAT_INIT_STATE>",
    "<WS>",
    "<NL>",
    "\"(\"",
    "\")\"",
    "\"[\"",
    "\"]\"",
    "\",\"",
    "\"!\"",
    "\";\"",
    "\".\"",
  };

}
