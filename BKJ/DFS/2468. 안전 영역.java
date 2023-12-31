import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 물에 잠기지 않는 안전한 영역의 개수 중에서 최대인 경우의 값을 구해야 한다. -> 1부터 배열의 max 높이까지 모든 경우를 계산 해야한다.
public class Main {
    static int N;
    static int[][] map;
    static boolean[][] isVisited;

    static int[] dr={-1,0,1,0};
    static int[] dc={0,1,0,-1};

    static int num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int max=0;

        // 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]>max){
                    max=map[i][j];
                }
            }
        }

        int answer=0;
        for(int m=0; m<=max; m++) {
            isVisited=new boolean[N][N];
            int count=0;
            num=m;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                	// 물에 잠기지 않는 경우를 탐색
                    if (map[i][j] > num && !isVisited[i][j]) {
                    	dfs(i, j);
                    	count++;
                    }
                }
            }
            if(count>answer){
                answer=count;
            }
        }
        
        // 출력
        System.out.println(answer);
    }
    
    // bfs로 풀이
    private static void bfs(int row, int col){
        Queue<int[]> queue= new ArrayDeque<>();
        queue.offer(new int[]{row,col});
        isVisited[row][col]=true;
        while(!queue.isEmpty()){
            int[] arr= queue.poll();
            for(int i=0;i<4;i++){
                int tr=arr[0]+dr[i];
                int tc=arr[1]+dc[i];
                if(tr<0||tr>=N||tc<0||tc>=N) continue; // 범위 설정
                if(map[tr][tc]>num && !isVisited[tr][tc]){
                    isVisited[tr][tc]=true;
                    queue.offer(new int[]{tr,tc});
                }
            }
        }
    }
    
    // dfs로 풀이
    private static void dfs(int row, int col){
        isVisited[row][col]=true;
        for(int i=0;i<4;i++){
            int tr=row+dr[i];
            int tc=col+dc[i];
            if(tr<0||tr>=N||tc<0||tc>=N) continue;
            if(map[tr][tc]>num && !isVisited[tr][tc]){
                dfs(tr,tc);
            }
        }
    }
}