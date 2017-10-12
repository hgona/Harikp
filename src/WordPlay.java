import java.util.*;
import java.io.*;
public class WordPlay {
        public static void main(String[] args)
        {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String word = input.nextLine();

            int length = word.length();
            String space = "";
            for(int count = 0; count < word.length(); count ++)
            {
                char c = word.charAt(count);
                if(c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O'
                        || c == 'u' || c == 'U')
                {
                    String front = word.substring(0, count);
                    String back = word.substring(count + 1);
                    word = front + "*" + back;
                }
            }
            if (length % 2 == 0)
            {
                space = "  ";
            }
            else
            {
                space = " ";
            }

            System.out.println(word);
        }

    }

