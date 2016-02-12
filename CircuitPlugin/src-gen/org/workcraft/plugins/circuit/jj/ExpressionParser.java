/* ExpressionParser.java */
/* Generated By:JavaCC: Do not edit this line. ExpressionParser.java */
package org.workcraft.plugins.circuit.jj;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

import org.workcraft.dom.Node;
import org.workcraft.util.Pair;
import org.workcraft.util.Triple;
import org.workcraft.exceptions.InvalidConnectionException;
import org.workcraft.exceptions.FormatException;
import org.workcraft.exceptions.NotFoundException;

import org.workcraft.plugins.circuit.expression.Expression;
import org.workcraft.plugins.circuit.expression.Formula;
import org.workcraft.plugins.circuit.expression.Term;
import org.workcraft.plugins.circuit.expression.Factor;
import org.workcraft.plugins.circuit.expression.Negation;
import org.workcraft.plugins.circuit.expression.Literal;
import org.workcraft.plugins.circuit.expression.Constant;

public class ExpressionParser implements ExpressionParserConstants {

  final public Expression parseExpression() throws ParseException {
    trace_call("parseExpression");
    try {Expression term;
        List<Expression> terms = new LinkedList<Expression>();
      term = parseTerm();
terms.add(term);
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case 9:
        case 10:
        case 11:{
          ;
          break;
          }
        default:
          jj_la1[0] = jj_gen;
          break label_1;
        }
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case 9:{
          jj_consume_token(9);
          break;
          }
        case 10:{
          jj_consume_token(10);
          break;
          }
        case 11:{
          jj_consume_token(11);
          break;
          }
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        term = parseTerm();
terms.add(term);
      }
if (terms.size() > 1) {
                        {if ("" != null) return new Formula(terms);}
                } else {
                        {if ("" != null) return term;}
                }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("parseExpression");
    }
  }

  final public Expression parseTerm() throws ParseException {
    trace_call("parseTerm");
    try {Expression factor;
        List<Expression> factors = new LinkedList<Expression>();
      factor = parseFactor();
factors.add(factor);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case CONST0:
        case CONST1:
        case NAME:
        case 12:
        case 13:
        case 14:
        case 15:
        case 16:
        case 17:{
          ;
          break;
          }
        default:
          jj_la1[2] = jj_gen;
          break label_2;
        }
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case 12:
        case 13:
        case 14:{
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case 12:{
            jj_consume_token(12);
            break;
            }
          case 13:{
            jj_consume_token(13);
            break;
            }
          case 14:{
            jj_consume_token(14);
            break;
            }
          default:
            jj_la1[3] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
          }
        default:
          jj_la1[4] = jj_gen;
          ;
        }
        factor = parseFactor();
factors.add(factor);
      }
if (factors.size() > 1) {
                        {if ("" != null) return new Term(factors);}
                } else {
                        {if ("" != null) return factor;}
                }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("parseTerm");
    }
  }

  final public Expression parseFactor() throws ParseException {
    trace_call("parseFactor");
    try {Expression expression;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NAME:{
        expression = parseLiteral();
        break;
        }
      case CONST0:
      case CONST1:{
        expression = parseConstant();
        break;
        }
      case 15:
      case 16:{
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case 15:{
          jj_consume_token(15);
          break;
          }
        case 16:{
          jj_consume_token(16);
          break;
          }
        default:
          jj_la1[5] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        expression = parseFactor();
{if ("" != null) return new Negation(expression);}
        break;
        }
      case 17:{
        jj_consume_token(17);
        expression = parseExpression();
        jj_consume_token(18);
{if ("" != null) return new Factor(expression);}
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
{if ("" != null) return expression;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("parseFactor");
    }
  }

  final public Expression parseLiteral() throws ParseException {
    trace_call("parseLiteral");
    try {Token nameToken;
      nameToken = jj_consume_token(NAME);
String name = nameToken.image;
                {if ("" != null) return new Literal(name);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("parseLiteral");
    }
  }

  final public Expression parseConstant() throws ParseException {
    trace_call("parseConstant");
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case CONST0:{
        jj_consume_token(CONST0);
{if ("" != null) return new Constant(false);}
        break;
        }
      case CONST1:{
        jj_consume_token(CONST1);
{if ("" != null) return new Constant(true);}
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("parseConstant");
    }
  }

  /** Generated Token Manager. */
  public ExpressionParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[8];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0xe00,0xe00,0x3f1c0,0x7000,0x7000,0x18000,0x381c0,0xc0,};
   }

  /** Constructor with InputStream. */
  public ExpressionParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public ExpressionParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ExpressionParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public ExpressionParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ExpressionParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
      jj_input_stream = new SimpleCharStream(stream, 1, 1);
   } else {
      jj_input_stream.ReInit(stream, 1, 1);
   }
   if (token_source == null) {
      token_source = new ExpressionParserTokenManager(jj_input_stream);
   }

    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public ExpressionParser(ExpressionParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ExpressionParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      trace_token(token, "");
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
      trace_token(token, " (in getNextToken)");
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[19];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 8; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 19; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  private int trace_indent = 0;
  private boolean trace_enabled = true;

/** Enable tracing. */
  final public void enable_tracing() {
    trace_enabled = true;
  }

/** Disable tracing. */
  final public void disable_tracing() {
    trace_enabled = false;
  }

  protected void trace_call(String s) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Call:   " + s);
    }
    trace_indent = trace_indent + 2;
  }

  protected void trace_return(String s) {
    trace_indent = trace_indent - 2;
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Return: " + s);
    }
  }

  protected void trace_token(Token t, String where) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Consumed token: <" + tokenImage[t.kind]);
      if (t.kind != 0 && !tokenImage[t.kind].equals("\"" + t.image + "\"")) {
        System.out.print(": \"" + TokenMgrError.addEscapes(t.image) + "\"");
      }
      System.out.println(" at line " + t.beginLine + " column " + t.beginColumn + ">" + where);
    }
  }

  protected void trace_scan(Token t1, int t2) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Visited token: <" + tokenImage[t1.kind]);
      if (t1.kind != 0 && !tokenImage[t1.kind].equals("\"" + t1.image + "\"")) {
        System.out.print(": \"" + TokenMgrError.addEscapes(t1.image) + "\"");
      }
      System.out.println(" at line " + t1.beginLine + " column " + t1.beginColumn + ">; Expected token: <" + tokenImage[t2] + ">");
    }
  }

}
