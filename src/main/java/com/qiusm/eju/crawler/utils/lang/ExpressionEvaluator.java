package com.qiusm.eju.crawler.utils.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 算数表达式逻辑验证；</br>
 * 2017-06-19 10:32:15</br>
 * 参考地址：http://blog.csdn.net/qin9r3y/article/details/8897513</br>
 * @author qiushengming
 *
 */
public class ExpressionEvaluator {

    private static Map<String, Integer> opLvl;

    static {
        init();
    }


    private static void init() {
        opLvl = new HashMap<String, Integer>();
        opLvl.put("||", 0);
        opLvl.put("&&", 1);
        opLvl.put(">", 2);
        opLvl.put("<", 2);
        opLvl.put(">=", 2);
        opLvl.put("<=", 2);
        opLvl.put("==", 2);
        opLvl.put("!=", 2);
        opLvl.put("+", 3);
        opLvl.put("-", 3);
        opLvl.put("*", 4);
        opLvl.put("/", 4);
        opLvl.put("sqr", 5);
        opLvl.put("(", -1);
        opLvl.put(")", -2);
    }


    /**
     * 计算中缀表达式
     * 
     * @param nifix 以算术或逻辑中缀表达式
     * @return 若为算术值则是Integer或Double实例，若为逻辑值则是Boolean实例
     * @throws Exception
     */
    public static Object calcNifix(String nifix) throws Exception {
        Object ret = null;
        Stack<String> opStack = new Stack<String>();
        Stack<Object> oaStack = new Stack<Object>();
        int length = nifix.length();
        int begin = 0;
        boolean numStat = true;

        for (int i = 0; i < length; i++) {
            char c = nifix.charAt(i);
            if (Character.isDigit(c) || c == '.') { // 遇到数值
                if (!numStat) {
                    throw new Exception(
                            "无法识别的运算符\"" + nifix.substring(begin, i) + "\"");
                }
                else {
                    numStat = true;
                }
            }
            else if (Character.isSpaceChar(c)) { // 遇到空白
                continue;
            }
            else { // 遇到运算符
                if (numStat) {
                    if (begin != i) {
                        String oa = nifix.substring(begin, i).trim();
                        if (!"".equals(oa)) {
                            try {
                                if (oa.indexOf(".") != -1) {
                                    oaStack.push(Double.parseDouble(oa));
                                }
                                else {
                                    oaStack.push(Integer.parseInt(oa));
                                }
                            }
                            catch (Exception e) {
                                throw new Exception("无法识别的数值：" + oa);
                            }
                        }
                    }
                    begin = i;
                    numStat = false;
                }
                String op = nifix.substring(begin, i + 1).trim();
                if (opLvl.containsKey(op)) {
                    if (">".equals(op) || "<".equals(op)) {
                        if (i + 1 < length && nifix.charAt(i + 1) == '=') {
                            continue;
                        }
                    }
                    int lvl = opLvl.get(op);
                    switch (lvl) {
                        case 5:
                            opStack.push(op);
                            break;
                        case -1:
                            opStack.push(op);
                            break;
                        case -2:
                            try {
                                calcStack(opStack, oaStack, true, -1);
                            }
                            catch (Exception e) {
                                throw new Exception(
                                        "第" + i + "列附近出现错误：" + e.getMessage());
                            }
                            if (!opStack.isEmpty()) {
                                String topOp = opStack.peek();
                                if (opLvl.get(topOp) >= 5) {
                                    opStack.pop();
                                    Double result = null;
                                    try {
                                        result = calcSingleOperator(
                                                oaStack.pop(), topOp);
                                    }
                                    catch (Exception e) {
                                        throw new Exception("第"
                                                + i + "列附近出现错误："
                                                + e.getMessage());
                                    }
                                    oaStack.push(result);
                                }
                            }
                            break;
                        default:
                            if (!opStack.isEmpty()) {
                                try {
                                    calcStack(opStack, oaStack, false, lvl);
                                }
                                catch (Exception e) {
                                    throw new Exception("第"
                                            + i + "列附近出现错误：" + e.getMessage());
                                }
                                opStack.push(op);
                            }
                            else {
                                opStack.push(op);
                            }
                            break;
                    }
                    begin = i + 1;
                    numStat = true;
                }
            }
        }
        if (!oaStack.isEmpty()) {
            if (!opStack.isEmpty()) {
                if (begin < length) {
                    String oa = nifix.substring(begin, length).trim();
                    if (!"".equals(oa)) {
                        try {
                            if (oa.indexOf(".") != -1) {
                                oaStack.push(Double.parseDouble(oa));
                            }
                            else {
                                oaStack.push(Integer.parseInt(oa));
                            }
                        }
                        catch (Exception e) {
                            throw new Exception("无法识别的数值：" + oa);
                        }
                    }
                }
                ret = calcStack(opStack, oaStack, false, Integer.MIN_VALUE);
            }
            else {
                return oaStack.pop();
            }
        }
        else {
            String str = nifix.trim();
            try {
                if (str.indexOf(".") != -1) {
                    ret = Double.parseDouble(nifix.trim());
                }
                else {
                    ret = Integer.parseInt(nifix.trim());
                }
            }
            catch (Exception e) {
                throw new Exception("无法识别的数值：" + str);
            }
        }

        return ret;
    }


    /**
     * 计算一元运算符
     */
    private static Double calcSingleOperator(Object oa, Object op)
        throws Exception {
        if ("sqr".equals(op)) {
            if (oa instanceof Integer) {
                return Math.sqrt((Integer) oa);
            }
            else if (oa instanceof Double) {
                return Math.sqrt((Double) oa);
            }
            else {
                throw new Exception("一元运算类型不匹配");
            }
        }
        else {
            throw new Exception("未知的一元运算符：" + op);
        }
    }


    /**
     * 二元运算符计算
     */
    private static Number calcCalculateOperator(Object oa1, Object oa2,
        String op) throws Exception {
        if ("+".equals(op)) {
            if (oa2 instanceof Integer && oa1 instanceof Integer) {
                return (Integer) oa2 + (Integer) oa1;
            }
            else if (oa2 instanceof Integer && oa1 instanceof Double) {
                return (Integer) oa2 + (Double) oa1;
            }
            else if (oa2 instanceof Double && oa1 instanceof Integer) {
                return (Double) oa2 + (Integer) oa1;
            }
            else if (oa2 instanceof Double && oa1 instanceof Double) {
                return (Double) oa2 + (Double) oa1;
            }
            else {
                throw new Exception("二元运算类型不匹配");
            }
        }
        else if ("-".equals(op)) {
            if (oa2 instanceof Integer && oa1 instanceof Integer) {
                return (Integer) oa2 - (Integer) oa1;
            }
            else if (oa2 instanceof Integer && oa1 instanceof Double) {
                return (Integer) oa2 - (Double) oa1;
            }
            else if (oa2 instanceof Double && oa1 instanceof Integer) {
                return (Double) oa2 - (Integer) oa1;
            }
            else if (oa2 instanceof Double && oa1 instanceof Double) {
                return (Double) oa2 - (Double) oa1;
            }
            else {
                throw new Exception("二元运算类型不匹配");
            }
        }
        else if ("*".equals(op)) {
            if (oa2 instanceof Integer && oa1 instanceof Integer) {
                return (Integer) oa2 * (Integer) oa1;
            }
            else if (oa2 instanceof Integer && oa1 instanceof Double) {
                return (Integer) oa2 * (Double) oa1;
            }
            else if (oa2 instanceof Double && oa1 instanceof Integer) {
                return (Double) oa2 * (Integer) oa1;
            }
            else if (oa2 instanceof Double && oa1 instanceof Double) {
                return (Double) oa2 * (Double) oa1;
            }
            else {
                throw new Exception("二元运算类型不匹配");
            }
        }
        else if ("/".equals(op)) {
            if (oa2 instanceof Integer && oa1 instanceof Integer) {
                return (Integer) oa2 / (Integer) oa1;
            }
            else if (oa2 instanceof Integer && oa1 instanceof Double) {
                return (Integer) oa2 / (Double) oa1;
            }
            else if (oa2 instanceof Double && oa1 instanceof Integer) {
                return (Double) oa2 / (Integer) oa1;
            }
            else if (oa2 instanceof Double && oa1 instanceof Double) {
                return (Double) oa2 / (Double) oa1;
            }
            else {
                throw new Exception("二元运算类型不匹配");
            }
        }
        else {
            throw new Exception("未知的二元运算符：" + op);
        }
    }


    /**
     * 计算逻辑运算符
     */
    private static Boolean calcLogicOperator(Object oa1, Object oa2, String op)
        throws Exception {
        if (oa1 instanceof Boolean && oa2 instanceof Boolean) {
            if ("||".equals(op)) {
                return (Boolean) oa2 || (Boolean) oa1;
            }
            else if ("&&".equals(op)) {
                return (Boolean) oa2 && (Boolean) oa1;
            }
            else {
                throw new Exception("未知的逻辑运算符：" + op);
            }
        }
        else {
            throw new Exception("逻辑判断类型不匹配");
        }

    }


    /**
     * 计算关系运算符
     */
    private static Boolean calcRelationOperator(Object oa1, Object oa2,
        String op) throws Exception {
        if (oa2 instanceof Integer && oa1 instanceof Integer) {
            if (">".equals(op)) {
                return (Integer) oa2 > (Integer) oa1;
            }
            else if ("<".equals(op)) {
                return (Integer) oa2 < (Integer) oa1;
            }
            else if (">=".equals(op)) {
                return ((Integer) oa2).intValue() >= ((Integer) oa1).intValue();
            }
            else if ("<=".equals(op)) {
                return ((Integer) oa2).intValue() <= ((Integer) oa1).intValue();
            }
            else if ("==".equals(op)) {
                return ((Integer) oa2).intValue() == ((Integer) oa1).intValue();
            }
            else if ("!=".equals(op)) {
                return ((Integer) oa2).intValue() != ((Integer) oa1).intValue();
            }
            else {
                throw new Exception("未知的关系运算符：" + op);
            }
        }
        else if (oa2 instanceof Integer && oa1 instanceof Double) {
            if (">".equals(op)) {
                return (Integer) oa2 > (Double) oa1;
            }
            else if ("<".equals(op)) {
                return (Integer) oa2 < (Double) oa1;
            }
            else if (">=".equals(op)) {
                return ((Integer) oa2).intValue() >= ((Double) oa1)
                        .doubleValue();
            }
            else if ("<=".equals(op)) {
                return ((Integer) oa2).intValue() <= ((Double) oa1)
                        .doubleValue();
            }
            else if ("==".equals(op)) {
                return ((Integer) oa2).intValue() == ((Double) oa1)
                        .doubleValue();
            }
            else if ("!=".equals(op)) {
                return ((Integer) oa2).intValue() != ((Double) oa1)
                        .doubleValue();
            }
            else {
                throw new Exception("未知的关系运算符：" + op);
            }
        }
        else if (oa2 instanceof Double && oa1 instanceof Integer) {
            if (">".equals(op)) {
                return (Double) oa2 > (Integer) oa1;
            }
            else if ("<".equals(op)) {
                return (Double) oa2 < (Integer) oa1;
            }
            else if (">=".equals(op)) {
                return ((Double) oa2).doubleValue() >= ((Integer) oa1)
                        .intValue();
            }
            else if ("<=".equals(op)) {
                return ((Double) oa2).doubleValue() <= ((Integer) oa1)
                        .intValue();
            }
            else if ("==".equals(op)) {
                return ((Double) oa2).doubleValue() == ((Integer) oa1)
                        .intValue();
            }
            else if ("!=".equals(op)) {
                return ((Double) oa2).doubleValue() != ((Integer) oa1)
                        .intValue();
            }
            else {
                throw new Exception("未知的关系运算符：" + op);
            }
        }
        else if (oa2 instanceof Double && oa1 instanceof Double) {
            if (">".equals(op)) {
                return (Double) oa2 > (Double) oa1;
            }
            else if ("<".equals(op)) {
                return (Double) oa2 < (Double) oa1;
            }
            else if (">=".equals(op)) {
                return ((Double) oa2).doubleValue() >= ((Double) oa1)
                        .doubleValue();
            }
            else if ("<=".equals(op)) {
                return ((Double) oa2).doubleValue() <= ((Double) oa1)
                        .doubleValue();
            }
            else if ("==".equals(op)) {
                return ((Double) oa2).doubleValue() == ((Double) oa1)
                        .doubleValue();
            }
            else if ("!=".equals(op)) {
                return ((Double) oa2).doubleValue() != ((Double) oa1)
                        .doubleValue();
            }
            else {
                throw new Exception("未知的关系运算符：" + op);
            }
        }
        else {
            throw new Exception("关系运算类型不匹配");
        }
    }


    /**
     * 计算运算符栈
     * 
     * @param opStack 运算符栈
     * @param oaStack 运算成员栈
     * @param popParenth 是否一直计算到左括号
     * @param lvlDeep 运算符级别深度
     * @return Integer或Double或Boolean的运算结果
     * @throws Exception
     */
    private static Object calcStack(Stack<String> opStack,
        Stack<Object> oaStack, boolean popParenth, int lvlDeep)
        throws Exception {
        Object ret = null;
        while (!opStack.isEmpty()) {
            String topOp = opStack.peek();
            if (!popParenth) {
                int lvl = opLvl.get(topOp);
                if (lvl >= lvlDeep) {
                    opStack.pop();
                    Object oa1 = oaStack.pop();
                    Object oa2 = oaStack.pop();
                    Object result = null;
                    if (lvl < 2) {
                        result = calcLogicOperator(oa1, oa2, topOp);
                    }
                    else if (lvl < 3) {
                        result = calcRelationOperator(oa1, oa2, topOp);
                    }
                    else if (lvl < 5) {
                        result = calcCalculateOperator(oa1, oa2, topOp);
                    }
                    oaStack.push(result);
                    ret = result;
                }
                else {
                    break;
                }
            }
            else {
                if (!"(".equals(topOp)) {
                    opStack.pop();
                    Object oa1 = oaStack.pop();
                    Object oa2 = oaStack.pop();
                    int lvl = opLvl.get(topOp);
                    Object result = null;
                    if (lvl < 2) {
                        result = calcLogicOperator(oa1, oa2, topOp);
                    }
                    else if (lvl < 3) {
                        result = calcRelationOperator(oa1, oa2, topOp);
                    }
                    else if (lvl < 5) {
                        result = calcCalculateOperator(oa1, oa2, topOp);
                    }
                    oaStack.push(result);
                    ret = result;
                }
                else {
                    opStack.pop();
                    break;
                }
            }
        }
        return ret;
    }


    public static void main(String[] args) throws Exception {
        if(11 != 1&& 12 != 2)
        // System.out.println(calcNifix("9+2*3+10/2"));
        System.out.println(calcNifix("(11 != 0 && 12 != 1) || (1!=0)"));
//        System.out.println(calcNifix(
//                "62202.00>=323.00+545.00+ 453.00+47.00+233.00+468.00+34.00+323.00+32.00+3213.00+3213.00+4342.00+34242.00+543.00+546.00+6757.00+213.00+3243.00+3432.00"));
//        System.out.println(calcNifix(" (2)+ (3-6)"));
//        System.out.println(calcNifix("sqr(4)"));
//        System.out.println(calcNifix(" 1+ 2- ( 3+( sqr( 4 )-5) +1) "));
//        System.out.println(calcNifix("9+(3-1)*3+10/2"));
    }
}
