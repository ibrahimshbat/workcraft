/* DotGParserTokenManager.java */
/* Generated By:JavaCC: Do not edit this line. DotGParserTokenManager.java */
package org.workcraft.plugins.fst.jj;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import org.workcraft.dom.Node;
import org.workcraft.plugins.fsm.Event;
import org.workcraft.plugins.fsm.State;
import org.workcraft.plugins.fst.Fst;
import org.workcraft.plugins.fst.Signal;
import org.workcraft.plugins.fst.Signal.Type;
import org.workcraft.plugins.fst.SignalEvent;
import org.workcraft.plugins.fst.SignalEvent.Direction;
import org.workcraft.util.Pair;
import org.workcraft.exceptions.FormatException;

/** Token Manager. */
@SuppressWarnings("unused")public class DotGParserTokenManager implements DotGParserConstants {

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_2(int pos, long active0){
   switch (pos)
   {
      case 0:
         if ((active0 & 0x3f80L) != 0L)
            return 4;
         return -1;
      case 1:
         if ((active0 & 0x3f80L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 1;
            return 5;
         }
         return -1;
      case 2:
         if ((active0 & 0x3f80L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 2;
            return 5;
         }
         return -1;
      case 3:
         if ((active0 & 0x1f80L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 3;
            return 5;
         }
         if ((active0 & 0x2000L) != 0L)
            return 5;
         return -1;
      case 4:
         if ((active0 & 0x1f80L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 4;
            return 5;
         }
         return -1;
      case 5:
         if ((active0 & 0x1b80L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 5;
            return 5;
         }
         if ((active0 & 0x400L) != 0L)
            return 5;
         return -1;
      case 6:
         if ((active0 & 0x800L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 14;
               jjmatchedPos = 5;
            }
            return -1;
         }
         if ((active0 & 0x1300L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 6;
            return 5;
         }
         if ((active0 & 0x80L) != 0L)
            return 5;
         return -1;
      case 7:
         if ((active0 & 0x800L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 14;
               jjmatchedPos = 5;
            }
            return -1;
         }
         if ((active0 & 0x200L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 7;
            return 5;
         }
         if ((active0 & 0x1100L) != 0L)
            return 5;
         return -1;
      case 8:
         if ((active0 & 0x800L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 14;
               jjmatchedPos = 5;
            }
            return -1;
         }
         if ((active0 & 0x200L) != 0L)
            return 5;
         return -1;
      case 9:
         if ((active0 & 0x800L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 14;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 10:
         if ((active0 & 0x800L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 14;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_2(int pos, long active0){
   return jjMoveNfa_2(jjStopStringLiteralDfa_2(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_2(){
   switch(curChar)
   {
      case 44:
         return jjStopAtPos(0, 22);
      case 46:
         return jjMoveStringLiteralDfa1_2(0x3f80L);
      case 47:
         return jjStopAtPos(0, 23);
      case 60:
         return jjStopAtPos(0, 20);
      case 61:
         return jjStopAtPos(0, 24);
      case 62:
         return jjStopAtPos(0, 21);
      case 123:
         return jjStopAtPos(0, 18);
      case 125:
         return jjStopAtPos(0, 19);
      default :
         return jjMoveNfa_2(3, 0);
   }
}
private int jjMoveStringLiteralDfa1_2(long active0){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_2(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 100:
         return jjMoveStringLiteralDfa2_2(active0, 0x400L);
      case 101:
         return jjMoveStringLiteralDfa2_2(active0, 0x2000L);
      case 105:
         return jjMoveStringLiteralDfa2_2(active0, 0x280L);
      case 109:
         return jjMoveStringLiteralDfa2_2(active0, 0x1000L);
      case 111:
         return jjMoveStringLiteralDfa2_2(active0, 0x100L);
      case 115:
         return jjMoveStringLiteralDfa2_2(active0, 0x800L);
      default :
         break;
   }
   return jjStartNfa_2(0, active0);
}
private int jjMoveStringLiteralDfa2_2(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_2(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_2(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa3_2(active0, 0x1000L);
      case 110:
         return jjMoveStringLiteralDfa3_2(active0, 0x2280L);
      case 116:
         return jjMoveStringLiteralDfa3_2(active0, 0x800L);
      case 117:
         return jjMoveStringLiteralDfa3_2(active0, 0x500L);
      default :
         break;
   }
   return jjStartNfa_2(1, active0);
}
private int jjMoveStringLiteralDfa3_2(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_2(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_2(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa4_2(active0, 0x800L);
      case 100:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_2(3, 13, 5);
         break;
      case 109:
         return jjMoveStringLiteralDfa4_2(active0, 0x400L);
      case 112:
         return jjMoveStringLiteralDfa4_2(active0, 0x80L);
      case 114:
         return jjMoveStringLiteralDfa4_2(active0, 0x1000L);
      case 116:
         return jjMoveStringLiteralDfa4_2(active0, 0x300L);
      default :
         break;
   }
   return jjStartNfa_2(2, active0);
}
private int jjMoveStringLiteralDfa4_2(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_2(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_2(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 101:
         return jjMoveStringLiteralDfa5_2(active0, 0x200L);
      case 107:
         return jjMoveStringLiteralDfa5_2(active0, 0x1000L);
      case 109:
         return jjMoveStringLiteralDfa5_2(active0, 0x400L);
      case 112:
         return jjMoveStringLiteralDfa5_2(active0, 0x100L);
      case 116:
         return jjMoveStringLiteralDfa5_2(active0, 0x800L);
      case 117:
         return jjMoveStringLiteralDfa5_2(active0, 0x80L);
      default :
         break;
   }
   return jjStartNfa_2(3, active0);
}
private int jjMoveStringLiteralDfa5_2(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_2(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_2(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 101:
         return jjMoveStringLiteralDfa6_2(active0, 0x800L);
      case 105:
         return jjMoveStringLiteralDfa6_2(active0, 0x1000L);
      case 114:
         return jjMoveStringLiteralDfa6_2(active0, 0x200L);
      case 116:
         return jjMoveStringLiteralDfa6_2(active0, 0x80L);
      case 117:
         return jjMoveStringLiteralDfa6_2(active0, 0x100L);
      case 121:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_2(5, 10, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_2(4, active0);
}
private int jjMoveStringLiteralDfa6_2(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_2(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_2(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 32:
         return jjMoveStringLiteralDfa7_2(active0, 0x800L);
      case 110:
         return jjMoveStringLiteralDfa7_2(active0, 0x1200L);
      case 115:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_2(6, 7, 5);
         break;
      case 116:
         return jjMoveStringLiteralDfa7_2(active0, 0x100L);
      default :
         break;
   }
   return jjStartNfa_2(5, active0);
}
private int jjMoveStringLiteralDfa7_2(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_2(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_2(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa8_2(active0, 0x200L);
      case 103:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_2(7, 12, 5);
         return jjMoveStringLiteralDfa8_2(active0, 0x800L);
      case 115:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_2(7, 8, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_2(6, active0);
}
private int jjMoveStringLiteralDfa8_2(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_2(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_2(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 108:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_2(8, 9, 5);
         break;
      case 114:
         return jjMoveStringLiteralDfa9_2(active0, 0x800L);
      default :
         break;
   }
   return jjStartNfa_2(7, active0);
}
private int jjMoveStringLiteralDfa9_2(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_2(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_2(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa10_2(active0, 0x800L);
      default :
         break;
   }
   return jjStartNfa_2(8, active0);
}
private int jjMoveStringLiteralDfa10_2(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_2(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_2(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 112:
         return jjMoveStringLiteralDfa11_2(active0, 0x800L);
      default :
         break;
   }
   return jjStartNfa_2(9, active0);
}
private int jjMoveStringLiteralDfa11_2(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_2(9, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_2(10, active0);
      return 11;
   }
   switch(curChar)
   {
      case 104:
         if ((active0 & 0x800L) != 0L)
            return jjStopAtPos(11, 11);
         break;
      default :
         break;
   }
   return jjStartNfa_2(10, active0);
}
private int jjStartNfaWithStates_2(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_2(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_2(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 12;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 17)
                        kind = 17;
                     { jjCheckNAdd(9); }
                  }
                  else if ((0x280000000000L & l) != 0L)
                  {
                     if (kind > 16)
                        kind = 16;
                  }
                  else if ((0x2400L & l) != 0L)
                  {
                     if (kind > 6)
                        kind = 6;
                     { jjCheckNAddTwoStates(1, 0); }
                  }
                  else if (curChar == 35)
                  {
                     if (kind > 5)
                        kind = 5;
                     { jjCheckNAddStates(0, 2); }
                  }
                  else if (curChar == 46)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 0:
                  if ((0x2400L & l) == 0L)
                     break;
                  kind = 6;
                  { jjCheckNAddTwoStates(1, 0); }
                  break;
               case 1:
                  if (curChar == 35)
                     { jjCheckNAddTwoStates(2, 0); }
                  break;
               case 2:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     { jjCheckNAddTwoStates(2, 0); }
                  break;
               case 5:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 14)
                     kind = 14;
                  jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 15)
                     kind = 15;
                  jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 8:
                  if ((0x280000000000L & l) != 0L && kind > 16)
                     kind = 16;
                  break;
               case 9:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  { jjCheckNAdd(9); }
                  break;
               case 10:
                  if (curChar != 35)
                     break;
                  if (kind > 5)
                     kind = 5;
                  { jjCheckNAddStates(0, 2); }
                  break;
               case 11:
                  if ((0xffffffffffffdbffL & l) == 0L)
                     break;
                  if (kind > 5)
                     kind = 5;
                  { jjCheckNAdd(11); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 15)
                        kind = 15;
                     { jjCheckNAdd(7); }
                  }
                  else if (curChar == 126)
                  {
                     if (kind > 16)
                        kind = 16;
                  }
                  break;
               case 2:
                  { jjAddStates(3, 4); }
                  break;
               case 4:
               case 5:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 14)
                     kind = 14;
                  { jjCheckNAdd(5); }
                  break;
               case 6:
               case 7:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 15)
                     kind = 15;
                  { jjCheckNAdd(7); }
                  break;
               case 8:
                  if (curChar == 126 && kind > 16)
                     kind = 16;
                  break;
               case 11:
                  if (kind > 5)
                     kind = 5;
                  jjstateSet[jjnewStateCnt++] = 11;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 2:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     { jjAddStates(3, 4); }
                  break;
               case 11:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjstateSet[jjnewStateCnt++] = 11;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 12 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_1(int pos, long active0){
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_1(int pos, long active0){
   return jjMoveNfa_1(jjStopStringLiteralDfa_1(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_1(){
   switch(curChar)
   {
      case 9:
         return jjStartNfaWithStates_1(0, 2, 0);
      case 32:
         return jjStartNfaWithStates_1(0, 1, 0);
      default :
         return jjMoveNfa_1(0, 0);
   }
}
private int jjStartNfaWithStates_1(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_1(state, pos + 1);
}
private int jjMoveNfa_1(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 1;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfffffff7ffffdbffL & l) == 0L)
                     break;
                  kind = 3;
                  jjstateSet[jjnewStateCnt++] = 0;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  kind = 3;
                  jjstateSet[jjnewStateCnt++] = 0;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 3)
                     kind = 3;
                  jjstateSet[jjnewStateCnt++] = 0;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 1 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_0(){
   switch(curChar)
   {
      default :
         return 1;
   }
}
static final int[] jjnextStates = {
   11, 2, 0, 2, 0, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, "\56\151\156\160\165\164\163", 
"\56\157\165\164\160\165\164\163", "\56\151\156\164\145\162\156\141\154", "\56\144\165\155\155\171", 
"\56\163\164\141\164\145\40\147\162\141\160\150", "\56\155\141\162\153\151\156\147", "\56\145\156\144", null, null, null, null, 
"\173", "\175", "\74", "\76", "\54", "\57", "\75", };
protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   if (jjmatchedPos < 0)
   {
      if (image == null)
         curTokenImage = "";
      else
         curTokenImage = image.toString();
      beginLine = endLine = input_stream.getEndLine();
      beginColumn = endColumn = input_stream.getEndColumn();
   }
   else
   {
      String im = jjstrLiteralImages[jjmatchedKind];
      curTokenImage = (im == null) ? input_stream.GetImage() : im;
      beginLine = input_stream.getBeginLine();
      beginColumn = input_stream.getBeginColumn();
      endLine = input_stream.getEndLine();
      endColumn = input_stream.getEndColumn();
   }
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 2;
int defaultLexState = 2;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(Exception e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      return matchedToken;
   }
   image = jjimage;
   image.setLength(0);
   jjimageLen = 0;

   switch(curLexState)
   {
     case 0:
       try { input_stream.backup(0);
          while (curChar <= 32 && (0x100000200L & (1L << curChar)) != 0L)
             curChar = input_stream.BeginToken();
       }
       catch (java.io.IOException e1) { continue EOFLoop; }
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_0();
       if (jjmatchedPos == 0 && jjmatchedKind > 4)
       {
          jjmatchedKind = 4;
       }
       break;
     case 1:
       jjmatchedKind = 3;
       jjmatchedPos = -1;
       curPos = 0;
       curPos = jjMoveStringLiteralDfa0_1();
       break;
     case 2:
       try { input_stream.backup(0);
          while (curChar <= 32 && (0x100000200L & (1L << curChar)) != 0L)
             curChar = input_stream.BeginToken();
       }
       catch (java.io.IOException e1) { continue EOFLoop; }
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_2();
       break;
   }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
           TokenLexicalActions(matchedToken);
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else
        {
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

void TokenLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

    /** Constructor. */
    public DotGParserTokenManager(SimpleCharStream stream){

      if (SimpleCharStream.staticFlag)
            throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");

    input_stream = stream;
  }

  /** Constructor. */
  public DotGParserTokenManager (SimpleCharStream stream, int lexState){
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Reinitialise parser. */
  public void ReInit(SimpleCharStream stream)
  {
	
    jjmatchedPos = jjnewStateCnt = 0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 12; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  public void ReInit( SimpleCharStream stream, int lexState)
  {
  
    ReInit( stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public void SwitchTo(int lexState)
  {
    if (lexState >= 3 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }

/** Lexer state names. */
public static final String[] lexStateNames = {
   "IGNORE_ALL",
   "IGNORE_LINE",
   "DEFAULT",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0x1fffff9L, 
};
static final long[] jjtoSkip = {
   0x6L, 
};
    protected SimpleCharStream  input_stream;

    private final int[] jjrounds = new int[12];
    private final int[] jjstateSet = new int[2 * 12];

    private final StringBuilder jjimage = new StringBuilder();
    private StringBuilder image = jjimage;
    private int jjimageLen;
    private int lengthOfMatch;
    
    protected int curChar;
}
