/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/


import java.util.Scanner;

public class KeyGen {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter login:" );
        String username = scn.next();
        String password = hashToString(hash(username));
        System.out.println("Login: "+username);
        System.out.println("Generated password: " + password);
    }

    private static int hash(String username) {
        int result = 0xfacc0fff;
        for (char e : username.toCharArray()) {
            int current = result ^ (e & 0xFF);
            int last_byte = (current & 0xff000000) >>> 24;
            current = (current << 8) + last_byte;
            result = current;
        }
        return result;
    }

    private static String hashToString(int hash) {
        String str = "";
        for (int i = 0; i < 8; i++) {
            int lastBits = hash & 0xF;
            hash >>= 4;
            if (lastBits > 9) {
                lastBits = 9;
            }
            str = str.concat(String.valueOf(lastBits));
        }
        return str;
    }

    private static byte[] inverseHashString(String hashString) {
        byte[] invHash = new byte[hashString.length()];
        for (int i = 0; i < hashString.length(); i++) {
            int current = hashString.charAt(i) & 0xFFFF;
            current = ~current;
            invHash[i] = (byte) (current & 0xFF);
        }
        return invHash;
    }
}