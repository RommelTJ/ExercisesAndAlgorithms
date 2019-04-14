package com.rommelrico.designpatterns.facade;

public class Main {

    public static void main(String[] args) {
        CPU cpu = new CPU();
        Memory memory = new Memory();
        HardDrive hd = new HardDrive();
        ComputerFacade computer = new ComputerFacade(cpu, memory, hd);
        computer.start();
    }

}
