package bullscows;

import java.util.*;

public class BullsAndCows {
    private static int a,b,c,d,turn,losc,nops;
    private static ArrayList<String> secretcode = new ArrayList<>();
    private static ArrayList<String> inputcode = new ArrayList<>();
    private static ArrayList randsecretcode = new ArrayList();
    private static StringBuilder stringrandsecretcode = new StringBuilder();
    public void play() {

        Scanner sc = new Scanner(System.in);

//        while (losc > 36){
//            System.out.println("Error: Can't generate a secret number with a length of 36.");
//            losc = sc.nextInt();
//        }
//        System.out.println("Input the number of possible symbols in the code: ");
//        nops = sc.nextInt();
//        while (nops > 36){
//            System.out.println("Error: Can't generate a secret number with a length of 36.");
//            nops = sc.nextInt();
//        }
//        System.out.println("Input the length of the secret code: ");
        String slocs = sc.next();
        try {
                losc = Integer.parseInt(slocs);
                if (losc > 36){
                    throw new Exception();
                }
            } catch (NumberFormatException e){
            System.out.println("Error: " + slocs + " isn't a valid number.");
            System.exit(0);

        }
        catch (Exception e){
            System.exit(0);
        }


        try {


            System.out.println("Input the number of possible symbols in the code: ");
            nops = sc.nextInt();
            if (nops > 36){
                throw new Exception();
            }
            else if (losc > nops || losc == 0){
                throw new Exception();
            }
            } catch (Exception e) {
            System.out.println("Error: it's not possible to generate a code with a length of " + losc + " with " + nops + " unique symbols.");
            System.exit(0);
        }
        int symbol = (nops - 10) + 96;
        Printer(nops,symbol);

        generateCode(nops,symbol);
        System.out.println(secretcode);
        System.out.println("Okay, let's start a game!");
        turn = 1;
        while (true) {
            System.out.println("Turn " + turn + ":");

            convertCode(losc);

            String note = checkCode(losc);

            if(note.equals("break")){break;}
            turn++;
        }
    }

    public static void Printer(int nops,int symbol) {
        String star = "";
        for (int i = 0; i < losc; i++) {
            star += "*";
        }
        if (nops == 0) {
            System.out.println("The secret is prepared: " + star + " (" + 0 + ")" + ".");
        }
        else if (nops <= 10) {
            System.out.println("The secret is prepared: " + star + " (" + 0 + "-" + (nops-1) + ")" + ".");
        }

        else {
            System.out.println("The secret is prepared: " + star + " (" + 0 + "-" + 9 + ", " + (char) 97 + "-" + (char) symbol + ")" + ".");
        }
    }

    public static void convertCode(int sCLength){
        inputcode.clear();

        Scanner sc = new Scanner(System.in);
        String code = sc.next();
            while (code.length() != sCLength) {
                System.out.println("Wrong input! Try Again.");
                code = sc.next();
            }
        toArray(inputcode,code);
    }

    public static void generateCode(int nops,int symbol) {


        if (nops > 10) {
            for (int i = 0; i < 10; i++) {
                randsecretcode.add(i);
            }
            // 97 - 122  a-z

            for (char i = 97; i <= symbol; i++) {
                randsecretcode.add(i);
            }
        }

        else {
            for (int i = 0; i < nops; i++) {
                randsecretcode.add(i);
            }
        }
        Collections.shuffle(randsecretcode);
        for (Object c: randsecretcode) {
            stringrandsecretcode.append(c);
        }
        toArray(secretcode,stringrandsecretcode.toString());
        }

    public static void toArray (ArrayList<String> code,String inputcode){
            List<String> list = List.of(String.valueOf(inputcode).split(""));
                for (String digit : list) {
                    if (code.size() < losc) {
                        code.add(digit);
                    }
            }
    }


    private static String checkCode(int sCLength){
        int x = 0,y = 0,a=0;
            for (String i : inputcode) {
                if (secretcode.get(a).equals(i)) {
                    x++;
                }
                if ((!secretcode.get(a).equals(i)) && secretcode.contains(i)) {
                    y++;
                }
                a++;
            }

             if (x == sCLength) {
                System.out.println("Grade: " + x + " bulls.\nCongratulations! You guessed the secret code.");
                return "break";
            } else if (y == sCLength) {
                 System.out.println("Grade: " + y +" cows.");
            } else if (x==1 && y ==1) {
                 System.out.println("Grade: " + x + " bull and " + y + " cow.");
             } else if (x != 0 && x <= sCLength && y == 0) {
                if (x==1){
                    System.out.println("Grade: " + x + " bull.");
                } else {
                    System.out.println("Grade: " + x + " bulls.");
                }
            } else if (x == 0 && y != 0 && y <= sCLength) {
                if (x==1){
                    System.out.println("Grade: " + y + " cow.");
                } else {
                    System.out.println("Grade: " + y + " cows.");
                }
            } else if (x != 0 && y != 0 && x <= sCLength && y <= sCLength) {
                if (x==1){
                    System.out.println("Grade: " + x + " bull and " + y + " cows.");
                } else if (y==1) {
                    System.out.println("Grade: " + x + " bulls and " + y + " cow.");
                } else {
                    System.out.println("Grade: " + x + " bulls and " + y + " cows.");
                }
            } else {
                System.out.println("Grade: None.");
            }
            return "";
    }
}
