package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    private Player player1, player2;
    private Board board;
    private int numPlayers;

    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        t.startGame();
    }

    public void startGame(){
        Scanner sc = new Scanner(System.in);
        //Take players input
        player1 = takePlayerInput(++numPlayers);
        player2 = takePlayerInput(++numPlayers);
        while(player1.getSymbol()==player2.getSymbol()){
            System.out.println("Symbol already taken!! Please enter the symbol again");
            player2.setSymbol(sc.next().charAt(0));
        }
        //Create the board
        board = new Board(player1.getSymbol(), player2.getSymbol());
        //Play the Game
        boolean playerTurn = true;
        int status = Board.INCOMPLETE;
        while(status==Board.INCOMPLETE || status==Board.INVALIDMOVE){
            if(playerTurn){
                System.out.println("Player 1- "+player1.getName()+"'s turn");
                System.out.println("Enter x: ");
                int x = sc.nextInt();
                System.out.println("Enter y: ");
                int y = sc.nextInt();

                status = board.move(player1.getSymbol(),x,y);
                if(status==Board.INVALIDMOVE){
                    System.out.println("Invalid move!! Please try again!!");
                    continue;
                }
            }
            else{
                System.out.println("Player 2- "+player2.getName()+"'s turn");
                System.out.println("Enter x: ");
                int x = sc.nextInt();
                System.out.println("Enter y: ");
                int y = sc.nextInt();

                status = board.move(player2.getSymbol(),x,y);
                if(status==Board.INVALIDMOVE){
                    System.out.println("Invalid move!! Please try again!!");
                    continue;
                }
            }
            playerTurn = !playerTurn;
            board.print();
        }
        if(status==Board.PLAYER1WINS){
            System.out.println("Player 1- "+player1.getName()+" Wins!!");
        } else if (status==Board.PLAYER2WINS) {
            System.out.println("Player 2- "+player2.getName()+" Wins!!");
        }else{
            System.out.println("DRAW!!");
        }

    }

    private Player takePlayerInput(int num){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Player" + num +"name: ");
        String name = sc.nextLine();
        System.out.println("Enter Player" + num +"symbol: ");
        char symbol = sc.next().charAt(0);
        Player p = new Player(name,symbol);
        return p;
    }
}
