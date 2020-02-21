package com.himank.all_about_java8.ch10_nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornExample1 {

    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine nashorn = manager.getEngineByName("nashorn");
        Integer eval = (Integer) nashorn.eval("10 + 20");
        System.out.println(eval);
    }
}
