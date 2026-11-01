package com.back;

public class Calc {
    public static int run(String expression) {
        String exp = expression.trim();

        // 덧셈·뺄셈은 왼쪽부터 계산해야 하므로 "마지막" 연산자를 기준으로 나눈다.
        // 이항 연산자는 양옆에 공백이 있어서 "-10" 같은 음수 부호와 구분된다.
        int plusMinusIndex = Math.max(exp.lastIndexOf(" + "), exp.lastIndexOf(" - "));
        if (plusMinusIndex != -1) {
            int left = run(exp.substring(0, plusMinusIndex));
            int right = run(exp.substring(plusMinusIndex + 3));

            return exp.charAt(plusMinusIndex + 1) == '+' ? left + right : left - right;
        }

        // 곱셈은 덧셈·뺄셈보다 우선순위가 높으므로 더 이상 +, - 가 없을 때 나눈다.
        int multiplyIndex = exp.lastIndexOf(" * ");
        if (multiplyIndex != -1) {
            return run(exp.substring(0, multiplyIndex)) * run(exp.substring(multiplyIndex + 3));
        }

        return Integer.parseInt(exp);
    }
}
