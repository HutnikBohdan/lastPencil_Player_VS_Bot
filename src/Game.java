import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Bot bot = new Bot();
        Player player = new Player();
        Scanner input = new Scanner(System.in);

        System.out.println("How many pencils would you like to use:");

        int startGame = 0;
        do {
            String lineP = input.nextLine();
            if (lineP.isEmpty()) {
                System.out.println("The number of pencils should be numeric");
                continue;
            }
            try {
                startGame = Integer.parseInt(lineP);
                if (startGame < 1) {
                    System.out.println("The number of pencils should be positive");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
                continue;
            }

        } while (true);



        System.out.println("Who will be the first (John, Jack):");
        String namePlayer1;
        String namePlayer2;
        do {
            namePlayer1 = input.next();
            if (!namePlayer1.equals("John") && !namePlayer1.equals("Jack")) {
                System.out.println("Choose between 'John' and 'Jack'");
                continue;
            } else {
                break;
            }
        } while (true);

        input.nextLine();


        if (namePlayer1.equals("John")) {
            namePlayer1 = String.format("%s's turn!", namePlayer1);
            namePlayer2 = "Jack's turn!";
        } else {
            namePlayer1 = String.format("%s's turn!", namePlayer1);
            namePlayer2 = "John's turn!";
        }



        int numPlayer1 = 0;
        boolean player1Win = false;
        boolean player2Win = false;
        String printGame = "";

        System.out.println("|".repeat(startGame));
        while (startGame > 0) {

            if ("Jack's turn!".equals(namePlayer2)) {

                System.out.println(namePlayer1);
                startGame = player.playerMove(startGame);
                if (startGame == 0) {
                    player2Win = true;
                    break;
                } else {
                    printGame = "|".repeat(startGame);
                    System.out.println(printGame);
                }


                System.out.println(namePlayer2);
                startGame = bot.botMove(startGame);
                if (startGame == 0) {
                    player1Win = true;
                    break;
                } else {
                    printGame = "|".repeat(startGame);
                    System.out.println(printGame);
                }


            } else if ("John's turn!".equals(namePlayer2))   {
                System.out.println(namePlayer2);
                startGame = bot.botMove(startGame);
                if (startGame == 0) {
                    player1Win = true;
                    break;
                } else {
                    printGame = "|".repeat(startGame);
                    System.out.println(printGame);
                }


                System.out.println(namePlayer1);
                startGame = player.playerMove(startGame);
                if (startGame == 0) {
                    player2Win = true;
                    break;
                } else {
                    printGame = "|".repeat(startGame);
                    System.out.println(printGame);
                }



            }
        }


        if (player1Win) {
            System.out.println(String.format("%s won!", namePlayer1.substring(0, 4)));
        } else {
            System.out.println(String.format("%s won!", namePlayer2.substring(0, 4)));
        }




    }
}


class Bot {


    public int botMove(int startGame) {
        Random random = new Random();
        int botMove = 0;
        if (startGame == 1) {
            botMove = 1;
            System.out.println(botMove);
            return startGame - botMove;
        }
        for (int i = startGame; i >= -3; i -= 4) {
            if (i == 5) {
                botMove = random.nextInt(3) + 1;
                break;
            } else if (i == 4) {
                botMove = 3;
                break;
            } else if (i == 3) {
                botMove = 2;
                break;
            } else if (i == 2) {
                botMove = 1;
                break;

            }
        }
        System.out.println(botMove);
        return startGame - botMove;
    }
}

class Player {
    Scanner input = new Scanner(System.in);

    public int playerMove(int startGame) {
        int playerMove = 0;
        do {
            String LineP = input.nextLine();
            if (LineP.isEmpty()) {
                System.out.println("Possible values: '1', '2' or '3'");
                continue;
            }
            try {
                playerMove = Integer.parseInt(LineP);
                if (playerMove > 3 || playerMove < 1) {
                    System.out.println("Possible values: '1', '2' or '3'");
                    continue;
                }
                if (playerMove > startGame) {
                    System.out.println("Too many pencils were taken");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Possible values: '1', '2' or '3'");
                continue;
            }

        } while (true);
        return startGame -= playerMove;
    }
}