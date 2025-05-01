package utils;

import java.util.Scanner;

public class Log {
    public static void info(String msg){
        String prefix = "[TáRolando?]: ";
        System.out.println(prefix + msg);
    }

    public static void input(String msg){
        String prefix = "[TáRolando?]-> ";
        System.out.print(prefix+ msg);
    }

    public static void ascii(){
        System.out.println("""
⠀⠀⠀⢀⣠⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⢤⡀⠀⠀
⠀⠀⠀⢾⠏⢁⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⢰⠙⡆⠱⡀⠀
⠀⠀⢀⠜⠒⠊⠉⢩⠭⠭⠍⣉⣉⣲⣒⣦⠤⠤⠴⣖⣉⡉⠙⠦⠵⣄⣹⠀
⠀⢀⠋⡤⡄⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⢠⠁⠀⠀⡖⢄⣵⣄
⢠⢎⠜⠀⢹⠀⢀⣸⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣘⡀⠀⢰⠁⠀⡜⢸
⠳⡘⣄⠀⠈⡏⠉⢀⡀⠀⠀⠉⠑⡆⠀⠀⢀⡤⠒⠉⠀⠈⠉⢺⠀⢀⢡⠇
⠀⠙⢬⣆⠀⢱⠀⠘⠈⠉⡹⠀⡜⠀⠀⡞⢉⣳⠤⠤⣀⣀⡤⠇⠀⠸⣯⡄
⠀⠀⠸⠏⠀⠈⡆⠀⡅⢰⠁⡰⠁⠀⠀⡇⢸⠀⠀⠀⡈⢀⠇⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⢡⠀⢳⢏⢶⠃⠀⠀⠀⣧⡎⠀⠀⠀⡇⢸⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠘⡄⢰⠈⠉⠀⠀⠀⢼⠴⠁⠀⠀⠀⠀⡌⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⢇⣸⡀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⠃⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠈⠒⠻⠀⠀⠀⠀⠀⠀⠀⠀⠘⠒⠚⠀⠀⠀⠀⠀⠀⠀
                """);
    }

    public static Object getUserInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
