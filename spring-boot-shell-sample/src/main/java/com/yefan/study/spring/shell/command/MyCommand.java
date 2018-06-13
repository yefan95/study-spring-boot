package com.yefan.study.spring.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Size;

@ShellComponent
public class MyCommand {

    @ShellMethod(value = "Add two integers together.", key = "sum")
    public int add(int a, int b) {
        return a + b;
    }

    @ShellMethod("Display stuff.")
    public String echo(int a, int b, int c) {
        return String.format("You said a=%d, b=%d, c=%d", a, b, c);
    }

    @ShellMethod("Add Numbers.")
    public float add(@ShellOption(arity = 3) float[] numbers) {
        return numbers[0] + numbers[1] + numbers[2];
    }

    @ShellMethod("Change password.")
    public String changePassword(@Size(min = 8, max = 40) String password) {
        return "Password successfully set to " + password;
    }

}
