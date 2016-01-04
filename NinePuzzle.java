import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
public class NinePuzzle {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String initial = scan.next();

		int level = 1;
		String endConfig = "123456789";
		ArrayList<String> checkedConfigs = new ArrayList<String>();
		checkedConfigs.add(initial);
		Queue<NinePuzzleState> states = new LinkedList<NinePuzzleState>();

		NinePuzzleState start = new NinePuzzleState(initial, level, null);
		if (start.currentConfig.equals(endConfig)){
			System.out.println(start.currentConfig);
		}

		String[] moves = start.getMoves();
		int currentCount = 0;
		for (String a : moves) {
			if (a!= null) {
				states.add(new NinePuzzleState(a,level,start)); 
				currentCount++;
			}
		}
		boolean finished = false;
		boolean finished2 = false;
		int futureCount = 0;
		while(level < 32 && !finished){
			level+=1;
			for (int i = 0; i < currentCount && !finished2;i++){
				NinePuzzleState temp = states.remove();
				String[] moves2 = temp.getMoves(); 
				for (String a : moves2){
					if (!(a == null) && !(a.equals(endConfig)) && !(checkedConfigs.contains(a))){
						checkedConfigs.add(a);
						futureCount+=1;
						states.add(new NinePuzzleState(a,level,temp));
					}
					else if ( !(a== null) && a.equals(endConfig)){
						Stack s1 = new Stack();
						s1.push(a);
						s1.push(temp.currentConfig);
						while (temp.previousConfig != null){
							s1.push(temp.previousConfig.currentConfig);
							temp = temp.previousConfig;
						}
						while (!(s1.isEmpty())){
							System.out.println(s1.pop()); 
						}
						finished = true;
						finished2 = true;
						break;
					}
				}
			}
			currentCount=futureCount;
		}
		if (level == 32){
			System.out.println("-1");
		}
	}

}
