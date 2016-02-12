/* DotGParser.java */
/* Generated By:JavaCC: Do not edit this line. DotGParser.java */
package org.workcraft.plugins.stg.jj;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

import org.workcraft.dom.Node;
import org.workcraft.plugins.stg.SignalTransition.Direction;
import org.workcraft.plugins.stg.SignalTransition.Type;
import org.workcraft.plugins.stg.*;
import org.workcraft.util.Pair;
import org.workcraft.util.Triple;
import org.workcraft.exceptions.InvalidConnectionException;
import org.workcraft.exceptions.FormatException;
import org.workcraft.exceptions.NotFoundException;

public class DotGParser implements DotGParserConstants {
        private HashMap<String, Type> signals;
        private HashSet<String> dummies;
        private HashMap<Pair<Node, Node>, STGPlace> implicitPlaces;
        private STG stg;

        private void init() {
                signals = new HashMap<String, Type>();
                dummies = new HashSet<String>();
                stg = new STG();
                implicitPlaces = new HashMap<Pair<Node, Node>, STGPlace>();
        }

        private Node getOrCreate (String name) {
                Node node = stg.getNodeByReference(name);
                if (node == null) {
                        node = stg.getNodeByReference(name + "/0");
                }
                if (node == null) {
                        if (dummies.contains(name)) {
                                node = stg.createDummyTransition(name, null);
                        } else if (signals.containsKey(name)) {
                                node = getOrCreate(Triple.of(name, Direction.TOGGLE, 0));
                        } else {
                                node = stg.createPlace(name, null);
                        }
                }
                return node;
        }

        private Node getOrCreate (Pair<String, Integer> ref) {
                String reference = stg.makeReference(ref);
                String name = ref.getFirst();
                Node node = stg.getNodeByReference(reference);
                if (node == null) {
                        if (dummies.contains(name)) {
                                DummyTransition dt = stg.createDummyTransition(null, null);
                                stg.setName(dt, reference, true);
                                dt.setName(name);
                                node = dt;
                        } else if (signals.containsKey(name)) {
                                node = getOrCreate(Triple.of(name, Direction.TOGGLE, ref.getSecond()));
                        }
                }
                return node;
        }

        private Node getOrCreate (Triple<String, Direction, Integer> ref) {
                String reference = stg.makeReference(ref);
                String name = ref.getFirst();
                Node node = stg.getNodeByReference (reference);
                if (node == null) {
                        SignalTransition st = stg.createSignalTransition();
                        stg.setName(st, reference, true);
                        if (signals.containsKey(name)) {
                                st.setSignalType(signals.get(name));
                                node = st;
                        } else {
                                throw new FormatException ("Undeclared signal encountered: "
                                        + name + " ("+reference+"). Possibly malformed header.");
                        }
                }
                return node;
        }

        private void createArc (Node first, Node second) {
                try {
                        ConnectionResult result = stg.connect(first, second);
                        STGPlace implicitPlace = result.getImplicitPlace();
                        if (implicitPlace != null) {
                                implicitPlaces.put(Pair.of(first, second), implicitPlace);
                                implicitPlace.setImplicit(true);
                        }
                } catch (InvalidConnectionException e) {
                        throw new FormatException ("Cannot create arc from " + stg.getNodeReference(first) +
                         " to " + stg.getNodeReference(second) + ".", e);
                }
        }

        private void addSignals (List<String> list, Type type) {
                for (String name : list) {
                        if (signals.containsKey(name)) {
                                Type prevType = signals.get(name);
                                if (!prevType.equals(type)) {
                                        throw new FormatException ("The " + type + " signal '" + name
                                           + "' was already listed as an " + prevType + " signal.");
                                }
                        } else if (dummies.contains(name)) {
                                throw new FormatException ("The " + type + " '" + name
                                    + "' was already listed as a dummy.");
                        } else {
                                signals.put(name, type);
                        }
                }
        }

        private void addDummies (List<String> list) {
                for (String name : list) {
                        if (signals.containsKey(name)) {
                                Type type = signals.get(name);
                                throw new FormatException ("The dummy '" + name
                                    + "' was already listed as an " + type + " signal.");
                        } else {
                                dummies.add(name);
                        }
                }
        }

  final public STG parse() throws ParseException {
init();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LINEBREAK:
      case INPUT_HEADER:
      case OUTPUT_HEADER:
      case INTERNAL_HEADER:
      case DUMMY_HEADER:
      case UNSUPPORTED_HEADER:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      header();
      jj_consume_token(LINEBREAK);
    }
    graph();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MARKING:
      case CAPACITY:
      case UNSUPPORTED_HEADER:{
        ;
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      footer();
      jj_consume_token(LINEBREAK);
    }
    jj_consume_token(END);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ANY:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_3;
      }
      jj_consume_token(ANY);
    }
    jj_consume_token(0);
{if ("" != null) return stg;}
    throw new Error("Missing return statement in function");
  }

  final public void header() throws ParseException {List < String > list;
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LINEBREAK:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_4;
      }
      jj_consume_token(LINEBREAK);
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INPUT_HEADER:{
      jj_consume_token(INPUT_HEADER);
      list = stringList();
addSignals(list, Type.INPUT);
      break;
      }
    case OUTPUT_HEADER:{
      jj_consume_token(OUTPUT_HEADER);
      list = stringList();
addSignals(list, Type.OUTPUT);
      break;
      }
    case INTERNAL_HEADER:{
      jj_consume_token(INTERNAL_HEADER);
      list = stringList();
addSignals(list, Type.INTERNAL);
      break;
      }
    case DUMMY_HEADER:{
      jj_consume_token(DUMMY_HEADER);
      list = stringList();
addDummies(list);
      break;
      }
    case UNSUPPORTED_HEADER:{
      jj_consume_token(UNSUPPORTED_HEADER);
      jj_consume_token(REST);
      break;
      }
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public List < String > stringList() throws ParseException {Token t;
    List < String > list = new LinkedList < String > ();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NAME:{
        ;
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        break label_5;
      }
      t = jj_consume_token(NAME);
list.add(t.image);
    }
{if ("" != null) return list;}
    throw new Error("Missing return statement in function");
  }

  final public void footer() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case MARKING:{
      jj_consume_token(MARKING);
      marking();
      break;
      }
    case CAPACITY:{
      jj_consume_token(CAPACITY);
      capacity();
      break;
      }
    case UNSUPPORTED_HEADER:{
      jj_consume_token(UNSUPPORTED_HEADER);
      jj_consume_token(REST);
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void capacity() throws ParseException {
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NAME:
      case 21:{
        ;
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        break label_6;
      }
      capacityEntry();
    }
  }

  final public void capacityEntry() throws ParseException {STGPlace p;
    int value;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 21:{
      p = implicitPlaceReference();
      break;
      }
    case NAME:{
      p = explicitPlaceReference();
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    value = assignment();
p.setCapacity(value);
  }

  final public void marking() throws ParseException {
    jj_consume_token(19);
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NAME:
      case 21:{
        ;
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        break label_7;
      }
      markingEntry();
    }
    jj_consume_token(20);
  }

  final public int assignment() throws ParseException {Token t;
    jj_consume_token(25);
    t = jj_consume_token(INTEGER);
{if ("" != null) return Integer.parseInt(t.image);}
    throw new Error("Missing return statement in function");
  }

  final public void markingEntry() throws ParseException {STGPlace p;
    int value = 1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 21:{
      p = implicitPlaceReference();
      break;
      }
    case NAME:{
      p = explicitPlaceReference();
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 25:{
      value = assignment();
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      ;
    }
p.setTokens(value);
  }

  final public STGPlace implicitPlaceReference() throws ParseException {Node t1, t2;
    Token t;
    Integer tokens = null;
    jj_consume_token(21);
    t1 = anyTransition();
    jj_consume_token(23);
    t2 = anyTransition();
    jj_consume_token(22);
{if ("" != null) return implicitPlaces.get(Pair.of(t1, t2));}
    throw new Error("Missing return statement in function");
  }

  final public STGPlace explicitPlaceReference() throws ParseException {Token t;
    String name;
    Integer tokens = null;
    t = jj_consume_token(NAME);
name = t.image;
{if ("" != null) return (STGPlace) stg.getNodeByReference(name);}
    throw new Error("Missing return statement in function");
  }

  final public void graph() throws ParseException {
    jj_consume_token(GRAPH);
    jj_consume_token(LINEBREAK);
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NAME:{
        ;
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        break label_8;
      }
      graphLine();
      jj_consume_token(LINEBREAK);
    }
  }

  final public Triple < String, Direction, Integer > signalTransition() throws ParseException {Token t;
    String name;
    Direction direction;
    Integer instance = null;
    t = jj_consume_token(NAME);
name = t.image;
    t = jj_consume_token(DIRECTION);
direction = Direction.fromString(t.image);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 24:{
      jj_consume_token(24);
      t = jj_consume_token(INTEGER);
instance = Integer.parseInt(t.image);
      break;
      }
    default:
      jj_la1[13] = jj_gen;
      ;
    }
{if ("" != null) return Triple.of(name, direction, instance);}
    throw new Error("Missing return statement in function");
  }

  final public Pair < String, Integer > dummyTransition() throws ParseException {Token t;
    String name;
    Integer instance = null;
    t = jj_consume_token(NAME);
name = t.image;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 24:{
      jj_consume_token(24);
      t = jj_consume_token(INTEGER);
instance = Integer.parseInt(t.image);
      break;
      }
    default:
      jj_la1[14] = jj_gen;
      ;
    }
{if ("" != null) return Pair.of(name, instance);}
    throw new Error("Missing return statement in function");
  }

  final public Node anyTransition() throws ParseException {Triple < String, Direction, Integer > r;
    Pair < String, Integer > r2;
    Node t;
    if (jj_2_1(2147483647)) {
      r = signalTransition();
t = getOrCreate(r);
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NAME:{
        r2 = dummyTransition();
t = getOrCreate(r2);
        break;
        }
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
  }

  final public void graphLine() throws ParseException {Token t;
    Node from, to;
    if (jj_2_2(2147483647)) {
      from = anyTransition();
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NAME:{
        t = jj_consume_token(NAME);
from = getOrCreate(t.image);
        break;
        }
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NAME:{
        ;
        break;
        }
      default:
        jj_la1[17] = jj_gen;
        break label_9;
      }
      if (jj_2_3(2147483647)) {
        to = anyTransition();
      } else {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case NAME:{
          t = jj_consume_token(NAME);
to = getOrCreate(t.image);
          break;
          }
        default:
          jj_la1[18] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
createArc(from, to);
    }
  }

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_3_2()
 {
    if (jj_scan_token(NAME)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(17)) {
    jj_scanpos = xsp;
    if (jj_scan_token(24)) return true;
    }
    return false;
  }

  private boolean jj_3_1()
 {
    if (jj_3R_10()) return true;
    return false;
  }

  private boolean jj_3R_11()
 {
    if (jj_scan_token(24)) return true;
    if (jj_scan_token(INTEGER)) return true;
    return false;
  }

  private boolean jj_3R_10()
 {
    if (jj_scan_token(NAME)) return true;
    if (jj_scan_token(DIRECTION)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_11()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3_3()
 {
    if (jj_scan_token(NAME)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(17)) {
    jj_scanpos = xsp;
    if (jj_scan_token(24)) return true;
    }
    return false;
  }

  /** Generated Token Manager. */
  public DotGParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[19];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x87c0,0xb000,0x10,0x40,0x8780,0x10000,0xb000,0x210000,0x210000,0x210000,0x210000,0x2000000,0x10000,0x1000000,0x1000000,0x10000,0x10000,0x10000,0x10000,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[3];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public DotGParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public DotGParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new DotGParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
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
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public DotGParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new DotGParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
      jj_input_stream = new SimpleCharStream(stream, 1, 1);
   } else {
      jj_input_stream.ReInit(stream, 1, 1);
   }
   if (token_source == null) {
      token_source = new DotGParserTokenManager(jj_input_stream);
   }

    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public DotGParser(DotGParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(DotGParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
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
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) {
       return;
    }

    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];

      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }

      for (int[] oldentry : jj_expentries) {
        if (oldentry.length == jj_expentry.length) {
          boolean isMatched = true;

          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              isMatched = false;
              break;
            }

          }
          if (isMatched) {
            jj_expentries.add(jj_expentry);
            break;
          }
        }
      }

      if (pos != 0) {
        jj_lasttokens[(jj_endpos = pos) - 1] = kind;
      }
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[26];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 19; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 26; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 3; i++) {
      try {
        JJCalls p = jj_2_rtns[i];

        do {
          if (p.gen > jj_gen) {
            jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
            switch (i) {
              case 0: jj_3_1(); break;
              case 1: jj_3_2(); break;
              case 2: jj_3_3(); break;
            }
          }
          p = p.next;
        } while (p != null);

        } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }

    p.gen = jj_gen + xla - jj_la; 
    p.first = token;
    p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
