package com.insigma.common.util;

import org.springframework.util.StringUtils;

public class ArrayUtils {

  public ArrayUtils() {
  }

  public static int find(byte src[], byte dest[], int offset) {

    return find(src, 0, src.length, dest, 0, dest.length, offset);
  }

  public static int find(byte src[], byte dest[]) {
    return find(src, 0, src.length, dest, 0, dest.length, 0);
  }

  public static int find2(byte src[], byte dest[], int offset) {
    return find2(src, 0, src.length, dest, 0, dest.length, offset);
  }

  public static int findStr(byte src[], String dest) {
    if (null == dest) {
      return -1;
    }
    return find(src, 0, src.length, dest.getBytes(), 0, dest.getBytes().length,
                0);
  }

  public static int findStr(byte src[], String dest, int offset) {
    if (null == dest) {
      return -1;
    }
    return find(src, 0, src.length, dest.getBytes(), 0, dest.getBytes().length,
                offset);
  }

  public static final byte[] replaceStr(byte src[], String oldPattern,
                                        String newPattern) {
    if (src==null) {
      return null;
    }
    if (oldPattern==null){
      return src;
    }
    if (newPattern==null){
       newPattern="";
    }

    return StringUtils.replace(new String(src), oldPattern, newPattern).getBytes();
  }

  public static final byte[] replace(byte src[], byte oldPattern[],
                                     byte newPattern[]) {

    if (src == null) {
      return null;
    }
    int cur = 0;
    int newCur = 0;
    if ( (cur = find(src, oldPattern, cur)) >= 0) {
      byte srcTemp[] = src;
      byte newTemp[] = newPattern;
      int ol = oldPattern.length;
      int nl = newPattern.length;
      int dl = newPattern.length - ol;
      byte temp[] = new byte[srcTemp.length *
          (newPattern.length / oldPattern.length + 1)];
      System.arraycopy(srcTemp, 0, temp, 0, cur);
      System.arraycopy(newTemp, 0, temp, cur, newTemp.length);
      cur += ol;
      newCur = cur + dl;
      int srcCur = cur;
      int destCur = dl;
      while ( (cur = find(src, oldPattern, cur)) > 0) {
        System.arraycopy(srcTemp, srcCur, temp, newCur, cur - srcCur);
        System.arraycopy(newTemp, 0, temp, (newCur + cur) - srcCur, nl);
        destCur += dl;
        cur += ol;
        newCur = cur + destCur;
        srcCur = cur;
      }
      System.arraycopy(srcTemp, srcCur, temp, newCur, srcTemp.length - srcCur);
      newCur += srcTemp.length - srcCur;
      byte result[] = new byte[newCur];
      System.arraycopy(temp, 0, result, 0, newCur);
      return result;
    }
    else {
      return src;
    }
  }

  public static final byte[] replace2(byte src[], byte oldPattern[],
                                      byte newPattern[]) {
    if (src == null) {
      return null;
    }
    int cur = 0;
    int newCur = 0;
    if ( (cur = find2(src, oldPattern,
                      cur)) >= 0) {
      byte srcTemp[] = src;
      byte newTemp[] = newPattern;
      int ol = oldPattern.length;
      int nl = newPattern.length;
      int dl = newPattern.length - ol;
      byte temp[] = new byte[srcTemp.length *
          (newPattern.length / oldPattern.length + 1)];
      System.arraycopy(srcTemp, 0, temp, 0, cur);
      System.arraycopy(newTemp, 0, temp, cur,
                       nl);
      cur += ol;
      newCur = cur + dl;
      int srcCur = cur;
      int destCur = dl;
      while ( (cur = find2(src, oldPattern,
                           cur)) > 0) {
        System.arraycopy(srcTemp, srcCur, temp,
                         newCur, cur - srcCur);
        System.arraycopy(newTemp, 0, temp,
                         (newCur + cur) - srcCur,
                         nl);
        destCur += dl;
        cur += ol;
        newCur = cur + destCur;
        srcCur = cur;
      }
      System.arraycopy(srcTemp, srcCur, temp,
                       newCur, srcTemp.length - srcCur);
      newCur += srcTemp.length - srcCur;
      byte result[] = new byte[newCur];
      System.arraycopy(temp, 0, result, 0, newCur);
      return result;
    }
    else {
      return src;
    }
  }

  private static int find(byte src[], int srcBegin, int srcLength, byte dest[],
                          int destBegin, int destLength, int offset) {

    if (offset >= srcLength) {
      return destLength != 0 ? -1 : srcLength;
    }
    if (offset < 0) {
      offset = 0;
    }
    if (destLength == 0) {
      return offset;
    }
    byte ch = dest[destBegin];
    int cur = srcBegin + offset;
    int end = srcBegin + (srcLength - destLength);

//    while(cur<=end){
//      if (src[cur]!=ch){
//        cur++;
//
//      }
//      else{
//        int srcCur = cur + 1;
//        int compEnd = (srcCur + destLength) - 1;
//        int destCur = destBegin + 1;
//        while (srcCur < compEnd){
//          if (src[srcCur++] != dest[destCur++]) {
//            cur++;
//            break;
//          }
//        }
//      }
//      if (cur > end)
//          return -1;
//      return cur - srcBegin;
//
//    }
//

    label_process:
        do {
      while (cur <= end && src[cur] != ch) {
        cur++;
      }
      if (cur > end) {
        return -1;
      }
      int srcCur = cur + 1;
      int compEnd = (srcCur + destLength) - 1;
      int destCur = destBegin + 1;
      while (srcCur < compEnd) {
        if (src[srcCur++] != dest[destCur++]) {
          cur++;
          continue label_process;
        }
      }
      return cur - srcBegin;
    }
    while (true);
  }

  private static int find2(byte src[], int srcBegin,
                           int srcLength, byte dest[],
                           int destBegin, int destLength,
                           int offset) {
    int lt = 60; //"<"
    int gt = 62; //">"
    boolean flag = false;
    if (offset >= srcLength) {
      return destLength != 0 ? -1 : srcLength;
    }
    if (offset < 0) {
      offset = 0;
    }
    if (destLength == 0) {
      return offset;
    }
    byte ch = dest[destBegin];
    int cur = srcBegin + offset;
    int end = srcBegin + (srcLength - destLength);
    label_process:
        do {
      while (cur <= end &&
             (src[cur] != ch || !flag)) {
        if (src[cur] == lt) {
          flag = true;
        }
        else
        if (src[cur] == gt) {
          flag = false;
        }
        cur++;
      }
      if (cur > end) {
        return -1;
      }
      int srcCur = cur + 1;
      int compEnd = (srcCur + destLength) - 1;
      int destCur = destBegin + 1;
      while (srcCur < compEnd) {
        if (src[srcCur++] != dest[destCur++]) {
          cur++;
          continue label_process;
        }
      }
      return cur - srcBegin;
    }
    while (true);
  }

}
