public class GamePiece{
    public int XPos;
    public int YPos;
    public int XMax;
    public int type;
    public int Turn;
    public String Block;
    public int Size;
    public boolean [][] Pos;

    //Block Positions
    public static final boolean [][][][] ALL = new boolean [7][][][]; //holds all possible states of pieces
    //T
    public static final boolean [][][] T = new boolean [4][][];
    public static final boolean [][] T_1 = {{false, true, false},{true,true,true},{false,false,false}};
    public static final boolean [][] T_2 = {{true, false, false},{true,true,false},{true,false,false}};
    public static final boolean [][] T_3 = {{true, true, true},{false,true,false},{false,false,false}};
    public static final boolean [][] T_4 = {{false, true, false},{true,true,false},{false,true,false}};
    //S
    public static final boolean [][][] S = new boolean [4][][];
    public static final boolean [][] S_1 = {{false,true,true},{true,true,false},{false,false,false}};
    public static final boolean [][] S_2 = {{true,false,false},{true,true,false},{false,true,false}};
    public static final boolean [][] S_3 = {{false,true,true},{true,true,false},{false,false,false}};
    public static final boolean [][] S_4 = {{true,false,false},{true,true,false},{false,true,false}};
    //Z
    public static final boolean [][][] Z = new boolean [4][][];
    public static final boolean [][] Z_1 = {{true,true,false},{false,true,true},{false,false,false}};
    public static final boolean [][] Z_2 = {{false,true,false},{true,true,false},{true,false,false}};
    public static final boolean [][] Z_3 = {{true,true,false},{false,true,true},{false,false,false}};
    public static final boolean [][] Z_4 = {{false,true,false},{true,true,false},{true,false,false}};
    //J
    public static final boolean [][][] J = new boolean [4][][];
    public static final boolean [][] J_1 = {{false,true,false},{false,true,false},{true,true,false}};
    public static final boolean [][] J_2 = {{true,false,false},{true,true,true},{false,false,false}};
    public static final boolean [][] J_3 = {{true,true,false},{true,false,false},{true,false,false}};
    public static final boolean [][] J_4 = {{true,true,true},{false,false,true},{false,false,false}};
    //L
    public static final boolean [][][] L = new boolean [4][][];
    public static final boolean [][] L_1 = {{true,false,false},{true,false,false},{true,true,false}};
    public static final boolean [][] L_2 = {{true,true,true},{true,false,false},{false,false,false}};
    public static final boolean [][] L_3 = {{true,true,false},{false,true,false},{false,true,false}};
    public static final boolean [][] L_4 = {{false,false,true},{true,true,true},{false,false,false}};
    //O
    public static final boolean [][][] O = new boolean [4][][];
    public static final boolean [][] O_1 = {{true,true}, {true, true}};
    public static final boolean [][] O_2 = {{true,true}, {true, true}};
    public static final boolean [][] O_3 = {{true,true}, {true, true}};
    public static final boolean [][] O_4 = {{true,true}, {true, true}};
    //I
    public static final boolean [][][] I = new boolean [4][][];
    public static final boolean [][] I_1 = {{true,false,false,false},{true,false,false,false}, {true,false,false,false}, {true,false,false,false}};
    public static final boolean [][] I_2 = {{true,true,true,true},{false,false,false,false}, {false,false,false,false}, {false,false,false,false}};
    public static final boolean [][] I_3 = {{true,false,false,false},{true,false,false,false}, {true,false,false,false}, {true,false,false,false}};
    public static final boolean [][] I_4 = {{true,true,true,true},{false,false,false,false}, {false,false,false,false}, {false,false,false,false}};

    public GamePiece(GamePiece temp){
        Block = temp.Block;
        XPos = temp.XPos;
        YPos = temp.YPos;
        Turn = temp.Turn;
        Pos = temp.Pos;
        Size = temp.Size;
        type = temp.type;
        XMax = temp.XMax;
    }
    
    public GamePiece(int X, int Y, String BlockType, int TurnPos){ //X, Y are the top left corner of the array, 
       Block = BlockType;                                                       //BlockType is the type of teh piece, TUrn Pos 
       if(BlockType.equals("T")){
           Pos = new boolean[3][3];
           type = 1;
       } else if(BlockType.equals("S")){
           Pos = new boolean[3][3];
           type = 2;
       } else if(BlockType.equals("Z")){
           Pos = new boolean[3][3];
           type = 3;
       }else if(BlockType.equals("J")){
           Pos = new boolean[3][3];
           type = 4;
       }else if(BlockType.equals("L")){
           Pos = new boolean[3][3];
           type = 5;
       } else if(BlockType.equals("O")){
           Pos = new boolean[2][2];
           type = 6;
       } else if(BlockType.equals("I")){
           Pos = new boolean[4][4];
           type = 7;
       } else {
           System.out.println("Error");
       }

       XPos = X;
       YPos = Y;
       Turn = TurnPos;
       init_Pieces();
       Pos = ALL[type-1][Turn];
       findMax();
       Size = Pos.length;
    }
    
    public void findMax(){
        int temp = 0;
        int temp2 = 0;
        for(int i = 0; i < Size; i++){
            for(int j = 0; j < Size; j++){
                if(Pos[i][j]) temp2 = j;
            }
            if(temp2 > temp) temp = temp2;
        }
        XMax = temp + 1;
    }

    public void init_Pieces(){
       ALL[0] = T;
       ALL[1] = S;
       ALL[2] = Z;
       ALL[3] = J;
       ALL[4] = L;
       ALL[5] = O;
       ALL[6] = I;
       T[0] = T_1;
       T[1] = T_2;
       T[2] = T_3;
       T[3] = T_4;
       S[0] = S_1;
       S[1] = S_2;
       S[2] = S_3;
       S[3] = S_4; 
       Z[0] = Z_1;
       Z[1] = Z_2;
       Z[2] = Z_3;
       Z[3] = Z_4;
       J[0] = J_1;
       J[1] = J_2;
       J[2] = J_3;
       J[3] = J_4;
       L[0] = L_1;
       L[1] = L_2;
       L[2] = L_3;
       L[3] = L_4;
       O[0] = O_1;
       O[1] = O_2;
       O[2] = O_3;
       O[3] = O_4; 
       I[0] = I_1;
       I[1] = I_2;
       I[2] = I_3;
       I[3] = I_4;
    }

    public void up(){
        Turn = (Turn + 1)%4;
        Pos = ALL[type-1][Turn];
        findMax();
    }

    public void down(){
        Turn = Turn - 1;
        if(Turn < 0) Turn += 4;
        Pos = ALL[type-1][Turn];
        findMax();
    }

    
}
