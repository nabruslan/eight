package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Node {
	boolean opened;
	int element[], empty, lvl, PathCost;
	Node LEFT, RIGHT, UP, DOWN, parent;
	String OPERATOR;
	boolean flag;
	Node() {
		PathCost = 0;
		opened = false;
		element = new int[9];
		empty = 0;
		LEFT = null;
		RIGHT = null;
		UP = null;
		DOWN = null;
		parent = null;
		lvl = 0;
		OPERATOR = null;
		flag = false;
	}
	
	void makeOpened() {
		opened = true;
	}
	
	void NodeOut() {
		System.out.printf("\nОператор %s; Уровень %d; Стоимость %d", OPERATOR, lvl, PathCost);
		System.out.printf("\n%d %d %d", element[0], element[1], element[2]);
		System.out.printf("\n%d %d %d", element[3], element[4], element[5]);
		System.out.printf("\n%d %d %d", element[6], element[7], element[8]);
		System.out.printf("\n");
	}
	
	void LEFT() {
		if (empty != 0 && empty!=3 && empty!=6) {
			LEFT = new Node();
			LEFT.parent = this;
			LEFT.lvl = lvl + 1;
			LEFT.empty = empty - 1;
			System.arraycopy(element, 0, LEFT.element, 0, 9);
			LEFT.element[empty] = element[LEFT.empty];
			LEFT.element[LEFT.empty] = 0;
			LEFT.OPERATOR = "LEFT";
		}
	}
	
	void RIGHT() {
		if (empty != 2 && empty!=5 && empty!=8) {
			RIGHT = new Node();
			RIGHT.parent = this;
			RIGHT.lvl = lvl + 1;
			RIGHT.empty = empty + 1;
			System.arraycopy(element, 0, RIGHT.element, 0, 9);
			RIGHT.element[empty] = element[RIGHT.empty];
			RIGHT.element[RIGHT.empty] = 0;
			RIGHT.OPERATOR = "RIGHT";
		}
	}
	
	void UP() {
		if (empty > 2) {
			UP = new Node();
			UP.parent = this;
			UP.lvl = lvl + 1;
			UP.empty = empty - 3;
			System.arraycopy(element, 0, UP.element, 0, 9);
			UP.element[empty] = element[UP.empty];
			UP.element[UP.empty] = 0;
			UP.OPERATOR = "UP";
		}
	}
	
	void DOWN() {
		if (empty < 6) {
			DOWN = new Node();
			DOWN.parent = this;
			DOWN.lvl = lvl + 1;
			DOWN.empty = empty + 3;
			System.arraycopy(element, 0, DOWN.element, 0, 9);
			DOWN.element[empty] = element[DOWN.empty];
			DOWN.element[DOWN.empty] = 0;
			DOWN.OPERATOR = "DOWN";
		}
	}
 
	boolean GoalTest(int steps) {
		if (element[0] == 6 && 
			element[1] == 8 &&
			element[2] == 1 && 
			element[3] == 5 &&
			element[4] == 2 && 
			element[5] == 0 &&
			element[6] == 4 && 
			element[7] == 3 &&
			element[8] == 7 && 
			empty == 5) { 
				System.out.printf("\nНАШЕЛ"); 
				System.out.printf("\nДостигнутое целевое состояние: "); 
				NodeOut(); 
				System.out.printf("Пройдено шагов: %d", steps);
				return true;
			}
		else
			return false;
	}
	
	boolean DoubleGoalTest(Node Node, int steps) {
		if(Node.LEFT == null && Node.RIGHT == null && Node.UP == null && Node.DOWN == null)
			if(Arrays.equals(element, Node.element)) {
				System.out.printf("\nНАШЕЛ");
				System.out.printf("\nУзел: ");
				NodeOut();
				System.out.printf("\nВстретился с узлом:");
				Node.NodeOut();
				System.out.printf("Пройдено шагов: %d", steps/2);
				return true;
			}
		else if(Node.LEFT != null)
			return DoubleGoalTest(Node.LEFT, steps);
		else if(Node.RIGHT != null)
			return DoubleGoalTest(Node.RIGHT, steps);
		if(Node.UP != null)
			return DoubleGoalTest(Node.UP, steps);
		if(Node.DOWN != null)
			return DoubleGoalTest(Node.DOWN, steps);
		return false;
				
	}
}

class Tree {
	
	Node root;
	int elementResult[];

	Tree() {
		root = new Node();
		elementResult = new int[9];
		CreateRoot();
		
	}
	
	int AStar(Node Node) {
		int k = 0;
		for(int i = 0; i < elementResult.length; i++)
			if(Node.element[i] != elementResult[i]) k++;
		return k;
	}
	
	void CreateRoot() {
		root.element[0] = 6;
		root.element[1] = 0;
		root.element[2] = 8;
		root.element[3] = 5;
		root.element[4] = 2;
		root.element[5] = 1;
		root.element[6] = 4;
		root.element[7] = 3;
		root.element[8] = 7;
		root.empty = 1;
		root.lvl = 0;
		elementResult[0] = 6;
		elementResult[1] = 8;
		elementResult[2] = 1;
		elementResult[3] = 5;
		elementResult[4] = 2;
		elementResult[5] = 0;
		elementResult[6] = 4;
		elementResult[7] = 3;
		elementResult[8] = 7;
	}
}

class TreeResult {
	
	Node rootStart, rootResult;

	TreeResult() {
		rootStart = new Node();
		rootResult = new Node();
		CreateRootStart();
		CreateRootResult();
	}
	
	void CreateRootStart() {
		rootStart.element[0] = 6;
		rootStart.element[1] = 0;
		rootStart.element[2] = 8;
		rootStart.element[3] = 5;
		rootStart.element[4] = 2;
		rootStart.element[5] = 1;
		rootStart.element[6] = 4;
		rootStart.element[7] = 3;
		rootStart.element[8] = 7;
		rootStart.empty = 1;
		rootStart.lvl = 0;
	}
	
	void CreateRootResult() {
		rootResult.element[0] = 6;
		rootResult.element[1] = 8;
		rootResult.element[2] = 1;
		rootResult.element[3] = 5;
		rootResult.element[4] = 2;
		rootResult.element[5] = 0;
		rootResult.element[6] = 4;
		rootResult.element[7] = 3;
		rootResult.element[8] = 7;
		rootResult.empty = 5;
		rootResult.lvl = 0;
	}
}


class AStar {
	Tree Tree;
	boolean GT;
	ArrayList<Node>AL;
	ArrayList<Node>ALE;
	int ch, steps;
	Scanner in;
	
	AStar (Tree Tree) {
		in = new Scanner(System.in);
		ch = 0;
		steps = 0;
		this.Tree = Tree;
		GT = false;
		AL = new ArrayList<Node>();
		ALE = new ArrayList<Node>();
		AL.add(this.Tree.root);
		ALE.add(this.Tree.root);
	}
	
	int Equals(Node Node) {
		for(int i = 0; i < AL.size(); i++) {
			if(Arrays.equals(Node.element, AL.get(i).element)) return i;
		}
		return -1;
	}
	
	boolean SearchChange(Node Node) {
		int i = Equals(Node);
		if(i != -1)
			if(AL.get(i).PathCost > Node.PathCost) {
				System.out.printf("Выявленный повтор: (с заменой)");
				Node.NodeOut();
				System.out.printf("И ЕГО КЛОООООН");
				AL.get(i).NodeOut();
				Node.makeOpened();
				Node.LEFT = AL.get(i).LEFT;
				Node.RIGHT = AL.get(i).RIGHT;
				Node.UP = AL.get(i).UP;
				Node.DOWN = AL.get(i).DOWN;
				AL.get(i).LEFT = null;
				AL.get(i).RIGHT = null;
				AL.get(i).UP = null;
				AL.get(i).DOWN = null;
				if(Node.LEFT != null)
					Node.LEFT.parent = Node;
				if(Node.RIGHT != null)
					Node.RIGHT.parent = Node;
				if(Node.UP != null)
					Node.UP.parent = Node;
				if(Node.DOWN != null)
					Node.DOWN.parent = Node;
				recount(Node);
				if(AL.get(i).OPERATOR == "LEFT")
					AL.get(i).parent.LEFT = null;
				if(AL.get(i).OPERATOR == "RIGHT")
					AL.get(i).parent.RIGHT = null;
				if(AL.get(i).OPERATOR == "UP")
					AL.get(i).parent.UP = null;
				if(AL.get(i).OPERATOR == "DOWN")
					AL.get(i).parent.DOWN = null;
				AL.remove(i);
				AL.add(Node);
				return true;
			}
		return false;
	}
	
	boolean SearchNotChange(Node Node) {
		int i = Equals(Node);
		if(i != -1) {
			System.out.printf("\nВыявленный повтор: ");
			Node.NodeOut();
			return true;
		}
		return false;
	}
	
	void recount(Node Node) {
		if(Node.LEFT != null) {
			Node.LEFT.PathCost = Tree.AStar(Node.LEFT) + Node.lvl + 1;
			Node.LEFT.lvl = Node.lvl + 1;
			recount(Node.LEFT);
		}
		if(Node.RIGHT != null) {
			Node.RIGHT.PathCost = Tree.AStar(Node.RIGHT) + Node.lvl + 1;
			Node.RIGHT.lvl = Node.lvl + 1;
			recount(Node.RIGHT);
		}
		if(Node.UP != null) {
			Node.UP.PathCost = Tree.AStar(Node.UP) + Node.lvl + 1;
			Node.UP.lvl = Node.lvl + 1;
			recount(Node.UP);
		}
		if(Node.DOWN != null) {
			Node.DOWN.PathCost = Tree.AStar(Node.DOWN) + Node.lvl + 1;
			Node.DOWN.lvl = Node.lvl + 1;
			recount(Node.DOWN);
		}
	}
	
	void Start() {
		CreateTree(Tree.root);
	}
	
	Node findMin() {
		Node Min;
		Min = new Node();
		Min = ALE.get(0);
		for(int i = 1; i < ALE.size(); i++)
			if(ALE.get(i).PathCost < Min.PathCost)
				Min = ALE.get(i);
		return Min;
	}
	
	void OutEnds(Node Node) {
		if(/*Node.LEFT == null && Node.RIGHT == null && Node.UP == null && Node.DOWN == null &&*/ !Node.opened)
			Node.NodeOut();
		if(Node.LEFT != null)
			OutEnds(Node.LEFT);
		if(Node.RIGHT != null)
			OutEnds(Node.RIGHT);
		if(Node.UP != null)
			OutEnds(Node.UP);
		if(Node.DOWN != null)
			OutEnds(Node.DOWN);		
	}
	
	void OutTree(Node Node) {
		steps++;
		System.out.printf("\nРаскрываемая вершина. Мы на уровне %d", Node.lvl);
		Node.NodeOut();
		ALE.remove(Node);
		Node.LEFT();
		if(Node.LEFT != null) Node.LEFT.PathCost = Tree.AStar(Node.LEFT) + Node.LEFT.lvl;
		Node.RIGHT();
		if(Node.RIGHT != null) Node.RIGHT.PathCost = Tree.AStar(Node.RIGHT) + Node.RIGHT.lvl;
		Node.UP();
		if(Node.UP != null) Node.UP.PathCost = Tree.AStar(Node.UP) + Node.UP.lvl;
		Node.DOWN();
		if( Node.DOWN != null) Node.DOWN.PathCost = Tree.AStar(Node.DOWN) + Node.DOWN.lvl;
		Node.makeOpened();
		System.out.printf("\nСозданные вершины: ");
		if(Node.LEFT != null) 
			Node.LEFT.NodeOut();
		if(Node.RIGHT != null) 
			Node.RIGHT.NodeOut();
		if(Node.DOWN != null) 
			Node.DOWN.NodeOut();
		if(Node.UP != null) 
			Node.UP.NodeOut();
		if(Node.LEFT != null) {
			GT = Node.LEFT.GoalTest(steps);
			if(!GT) {	
				if(!SearchChange(Node.LEFT))
					if(!SearchNotChange(Node.LEFT)) {
						ALE.add(Node.LEFT);
						AL.add(Node.LEFT);
					}
					else Node.LEFT = null;
			}
		}
		if(!GT && Node.RIGHT != null) {
			GT = Node.RIGHT.GoalTest(steps);
			if(!GT) {	
				if(!SearchChange(Node.RIGHT))
					if(!SearchNotChange(Node.RIGHT)) {
						ALE.add(Node.RIGHT);
						AL.add(Node.RIGHT);
					}
					else Node.RIGHT = null;
			}
		}
		if(!GT && Node.UP != null) {
			GT = Node.UP.GoalTest(steps);
			if(!GT) {	
				if(!SearchChange(Node.UP))
					if(!SearchNotChange(Node.UP)) {
						ALE.add(Node.UP);
						AL.add(Node.UP);
					}
					else Node.UP = null;
			}
		}
		if(!GT && Node.DOWN != null) {
			GT = Node.DOWN.GoalTest(steps);
			if(!GT) {	
				if(!SearchChange(Node.DOWN))
					if(!SearchNotChange(Node.DOWN)) {
						ALE.add(Node.DOWN);
						AL.add(Node.DOWN);
					}
					else Node.DOWN = null;
			}
		}
	}
	
	void CreateTree(Node Node) {
		if(ch == 0) {
			System.out.printf("Выбран пошаговый режим. Сменить режим? (1 - да, 0 - нет): ");
			ch = in.nextInt();
			OutTree(Node);
			if(!GT) {
				System.out.printf("\nКайма: ");
				OutEnds(Tree.root);
				CreateTree(findMin());
			}
		}
		else {
			OutTree(Node);
			if(!GT) {
				System.out.printf("\nКайма: ");
				OutEnds(Tree.root);
				CreateTree(findMin());
			}
		}				
	}
}

class Depth {
	int ch, steps;
	Scanner in;
	boolean GT; 
	Node root;
	ArrayList<int[]>AL;
	
	Depth (Node root) {
		steps = 0;
		ch = 0;
		in = new Scanner(System.in);
		this.root = root;
		AL = new ArrayList<int[]>();
		AL.add(this.root.element);
	}
	
	void OutEnds(Node Node) {
		if(/*Node.LEFT == null && Node.RIGHT == null && Node.UP == null && Node.DOWN == null &&*/ !Node.opened)
			Node.NodeOut();
		if(Node.LEFT != null)
			OutEnds(Node.LEFT);
		if(Node.RIGHT != null)
			OutEnds(Node.RIGHT);
		if(Node.UP != null)
			OutEnds(Node.UP);
		if(Node.DOWN != null)
			OutEnds(Node.DOWN);		
	}
	
	boolean Equals(int[] element) {
		for(int i = 0; i < AL.size(); i++)
			if(Arrays.equals(element, AL.get(i))) return true;
		return false;
	}
	
	boolean Search(Node Node) {
		if (Equals(Node.element))
			return true;
		else {
			AL.add(Node.element);
			return false;
		}
	}
	
	void CreateTree(Node Node) {
		if(Node != null) {
			Node.LEFT();
			/*if (Node.LEFT != null)
				GT = Node.LEFT.GoalTest();*/
			Node.RIGHT();
			/*if (Node.RIGHT != null && !GT)
				GT = Node.RIGHT.GoalTest();*/
			Node.UP();
			/*if (Node.UP != null && !GT)
				GT = Node.UP.GoalTest();*/
			Node.DOWN();
			/*if (Node.DOWN != null && !GT)
				GT = Node.DOWN.GoalTest();*/
			Node.makeOpened();
			if (ch == 0) {
				System.out.printf("Выбран пошаговый режим. Сменить режим? (1 - да, 0 - нет): ");
				ch = in.nextInt();
				steps++;
				System.out.printf("\nРаскрываемая вершина. Мы на уровне %d", Node.lvl);
				Node.NodeOut();
				System.out.printf("\nРаскрытые узлы: ");
				if(Node.LEFT != null) {Node.LEFT.NodeOut(); GT = Node.LEFT.GoalTest(steps);} 
				if(Node.RIGHT != null && !GT) {Node.RIGHT.NodeOut(); GT = Node.RIGHT.GoalTest(steps);}
				if(Node.UP != null && !GT) {Node.UP.NodeOut(); GT = Node.UP.GoalTest(steps);}
				if(Node.DOWN != null && !GT) {Node.DOWN.NodeOut(); GT = Node.DOWN.GoalTest(steps);}
				if(!GT) System.out.printf("\nВыявленные повторы: ");
				if(Node.LEFT != null && !GT)
					if(Search(Node.LEFT)) {
						Node.LEFT.NodeOut();
						Node.LEFT = null;
					}
				if(Node.RIGHT != null && !GT)
					if(Search(Node.RIGHT)) {
						Node.RIGHT.NodeOut();
						Node.RIGHT = null;
					}
				if(Node.UP != null && !GT)
					if(Search(Node.UP)) {
						Node.UP.NodeOut();
						Node.UP = null;
					}
				if(Node.DOWN != null && !GT)
					if(Search(Node.DOWN)) {
						Node.DOWN.NodeOut();
						Node.DOWN = null;
					}
				if(!GT) {
					System.out.printf("\nКайма: ");
					OutEnds(root);
				}
			}
			else {
				steps++;
				System.out.printf("\nРаскрываемая вершина. Мы на уровне %d", Node.lvl);
				Node.NodeOut();
				System.out.printf("\nРаскрытые узлы: ");
				if(Node.LEFT != null) {Node.LEFT.NodeOut(); GT = Node.LEFT.GoalTest(steps);}
				if(Node.RIGHT != null && !GT) {Node.RIGHT.NodeOut(); GT = Node.RIGHT.GoalTest(steps);}
				if(Node.UP != null && !GT) {Node.UP.NodeOut(); GT = Node.UP.GoalTest(steps);}
				if(Node.DOWN != null && !GT) {Node.DOWN.NodeOut(); GT = Node.DOWN.GoalTest(steps);}
				if(!GT) System.out.printf("\nВыявленные повторы: ");
				if(Node.LEFT != null && !GT)
					if(Search(Node.LEFT)) {
						Node.LEFT.NodeOut();
						Node.LEFT = null;
					}
				if(Node.RIGHT != null && !GT)
					if(Search(Node.RIGHT)) {
						Node.RIGHT.NodeOut();
						Node.RIGHT = null;
					}
				if(Node.UP != null && !GT)
					if(Search(Node.UP)) {
						Node.UP.NodeOut();
						Node.UP = null;
					}
				if(Node.DOWN != null && !GT)
					if(Search(Node.DOWN)) {
						Node.DOWN.NodeOut();
						Node.DOWN = null;
					}
				if(!GT) {
					System.out.printf("\nКайма: ");
					OutEnds(root);
				}
			}
			//if(!GT) GT=Node.GoalTest();
			if (!GT && Node.LEFT != null /*&& !Search(Node.LEFT)*/) {
				//System.out.printf("\nЛЕВЫЙ УЗЕЛ %d", Node.LEFT.lvl);
				//Node.LEFT.NodeOut();
				GT=Node.LEFT.GoalTest(steps);
				if(!GT)
					CreateTree(Node.LEFT);
				else
					System.out.printf("Пройдено шагов: %d", steps);
			}
			/*else
				Node.LEFT = null;*/
			if(!GT && Node.RIGHT != null /*&& !Search(Node.RIGHT)*/) {
				//System.out.printf("\nПРАВЫЙ УЗЕЛ %d", Node.RIGHT.lvl);
				//Node.RIGHT.NodeOut();
				GT=Node.RIGHT.GoalTest(steps);
				if(!GT)
					CreateTree(Node.RIGHT);
				else
					System.out.printf("Пройдено шагов: %d", steps);
			}
			/*else
				Node.RIGHT = null;*/
			if(!GT && Node.UP != null /*&& !Search(Node.UP)*/) {
				//System.out.printf("\nВЕРХНИЙ УЗЕЛ %d", Node.UP.lvl);
				//Node.UP.NodeOut();
				GT=Node.UP.GoalTest(steps);
				if(!GT)
					CreateTree(Node.UP);
				else
					System.out.printf("Пройдено шагов: %d", steps);
			}
			/*else
				Node.UP = null;*/
			if(!GT && Node.DOWN != null /*&& !Search(Node.DOWN)*/) {
				//System.out.printf("\nНИЖНИЙ УЗЕЛ %d", Node.DOWN.lvl);
				//Node.DOWN.NodeOut();
				GT=Node.DOWN.GoalTest(steps);
				if(!GT)
					CreateTree(Node.DOWN);
				else
					System.out.printf("Пройдено шагов: %d", steps);
			}
			/*else 
				Node.DOWN = null;*/	
		}
	}
}

class DoubleSearch {
	Scanner in;
	int ch, steps;
	boolean GT; 
	Node rootStart, rootResult;
	Object mutex = new Object();
	ArrayList<int[]>ALStart; ArrayList<int[]>ALResult;
	
	DoubleSearch (Node rootStart, Node rootResult) {
		steps = 0;
		ch = 0;
		in = new Scanner(System.in);
		this.rootStart = rootStart;
		this.rootResult = rootResult;
		ALStart = new ArrayList<int[]>();
		ALResult = new ArrayList<int[]>();
		ALStart.add(this.rootStart.element);
		ALResult.add(this.rootResult.element);
	}
	
	void OutEnds(Node Node) {
		if(!Node.opened)
			Node.NodeOut();
		if(Node.LEFT != null)
			OutEnds(Node.LEFT);
		if(Node.RIGHT != null)
			OutEnds(Node.RIGHT);
		if(Node.UP != null)
			OutEnds(Node.UP);
		if(Node.DOWN != null)
			OutEnds(Node.DOWN);		
	}
	
	boolean Equals(int[] element, ArrayList<int[]>AL) {
		for(int i = 0; i < AL.size(); i++)
			if(Arrays.equals(element, AL.get(i))) return true;
		return false;
	}
	
	boolean Search(Node Node, ArrayList<int[]>AL) {
		if (Equals(Node.element, AL))
			return true;
		else {
			AL.add(Node.element);
			return false;
		}
	}
	
	void start() {
		new MyUprtThread(false).start();
		new MyUprtThread(true).start();
	}
	
	class MyUprtThread extends Thread {
		boolean type;
		
		MyUprtThread(boolean type) {
			this.type = type;
		}
		
		public void run() {
			if(!type) {
				synchronized(mutex) {
					CreateTreeStart(rootStart);
				}
			}
			if(type) {
				synchronized(mutex) {
					CreateTreeResult(rootResult);
				}
			}
		}
	}
	
	void CreateTreeStart(Node Node) {
		mutex.notifyAll();
		try {mutex.wait();} catch (InterruptedException e) {e.printStackTrace();}
		if(Node != null) {
			Node.LEFT();
			Node.RIGHT();
			Node.UP();
			Node.DOWN();
			Node.makeOpened();
			if (ch == 0) {
				System.out.printf("Выбран пошаговый режим. Сменить режим? (1 - да, 0 - нет): ");
				ch = in.nextInt();
				steps++;
				System.out.printf("\n *дерево 1* Раскрываемая вершина. Мы на уровне %d", Node.lvl);
				Node.NodeOut();
				System.out.printf("\nРаскрытые узлы: ");
				if(Node.LEFT != null) {Node.LEFT.NodeOut(); GT = Node.LEFT.DoubleGoalTest(rootResult, steps);}
				if(Node.RIGHT != null && !GT) {Node.RIGHT.NodeOut(); GT = Node.RIGHT.DoubleGoalTest(rootResult, steps);}
				if(Node.UP != null && !GT) {Node.UP.NodeOut(); GT = Node.UP.DoubleGoalTest(rootResult, steps);}
				if(Node.DOWN != null && !GT) {Node.DOWN.NodeOut(); GT = Node.DOWN.DoubleGoalTest(rootResult, steps);}
				if(!GT) System.out.printf("\nВыявленные повторы: ");
				if(Node.LEFT != null && !GT)
					if(Search(Node.LEFT, ALStart)) {
						Node.LEFT.NodeOut();
						Node.LEFT = null;
					}
				if(Node.RIGHT != null && !GT)
					if(Search(Node.RIGHT, ALStart)) {
						Node.RIGHT.NodeOut();
						Node.RIGHT = null;
					}
				if(Node.UP != null && !GT)
					if(Search(Node.UP, ALStart)) {
						Node.UP.NodeOut();
						Node.UP = null;
					}
				if(Node.DOWN != null && !GT)
					if(Search(Node.DOWN, ALStart)) {
						Node.DOWN.NodeOut();
						Node.DOWN = null;
					}
				if(!GT) {
					System.out.printf("\nКайма: ");
					OutEnds(rootStart);
				}
			}
			else {
				steps++;
				System.out.printf("\n *дерево 1* Раскрываемая вершина. Мы на уровне %d", Node.lvl);
				Node.NodeOut();
				System.out.printf("\nРаскрытые узлы: ");
				if(Node.LEFT != null) {Node.LEFT.NodeOut(); GT = Node.LEFT.DoubleGoalTest(rootResult, steps);}
				if(Node.RIGHT != null && !GT) {Node.RIGHT.NodeOut(); GT = Node.RIGHT.DoubleGoalTest(rootResult, steps);}
				if(Node.UP != null && !GT) {Node.UP.NodeOut(); GT = Node.UP.DoubleGoalTest(rootResult, steps);}
				if(Node.DOWN != null && !GT) {Node.DOWN.NodeOut(); GT = Node.DOWN.DoubleGoalTest(rootResult, steps);}
				if(!GT) System.out.printf("\nВыявленные повторы: ");
				if(Node.LEFT != null  && !GT)
					if(Search(Node.LEFT, ALStart)) {
						Node.LEFT.NodeOut();
						Node.LEFT = null;
					}
				if(Node.RIGHT != null  && !GT)
					if(Search(Node.RIGHT, ALStart)) {
						Node.RIGHT.NodeOut();
						Node.RIGHT = null;
					}
				if(Node.UP != null  && !GT)
					if(Search(Node.UP, ALStart)) {
						Node.UP.NodeOut();
						Node.UP = null;
					}
				if(Node.DOWN != null  && !GT)
					if(Search(Node.DOWN, ALStart)) {
						Node.DOWN.NodeOut();
						Node.DOWN = null;
					}
				if(!GT) {
					System.out.printf("\nКайма: ");
					OutEnds(rootStart);
				}
			}
			//GT=Node.DoubleGoalTest(rootResult);
			if (!GT && Node.LEFT != null /*&& !Search(Node.LEFT, ALStart)*/) {
				/*System.out.printf("\n *дерево 1* ЛЕВЫЙ УЗЕЛ %d", Node.LEFT.lvl);
				Node.LEFT.NodeOut();
				GT=Node.LEFT.DoubleGoalTest(rootResult);*/
				if(!GT)
					CreateTreeStart(Node.LEFT);
				else {
						System.out.printf("Пройдено шагов: %d", steps);
						try {mutex.wait();} catch (InterruptedException e) {e.printStackTrace();}}
				}
			/*else
				Node.LEFT = null;*/
			if(!GT && Node.RIGHT != null /*&& !Search(Node.RIGHT, ALStart)*/) {
				/*System.out.printf("\n *дерево 1* ПРАВЫЙ УЗЕЛ %d", Node.RIGHT.lvl);
				Node.RIGHT.NodeOut();*/
				//GT=Node.RIGHT.DoubleGoalTest(rootResult);
				if(!GT)
					CreateTreeStart(Node.RIGHT);
				else {
						System.out.printf("Пройдено шагов: %d", steps);
						try {mutex.wait();} catch (InterruptedException e) {e.printStackTrace();}}
			}
			/*else
				Node.RIGHT = null;*/
			if(!GT && Node.UP != null /*&& !Search(Node.UP, ALStart)*/) {
				/*System.out.printf("\n *дерево 1* ВЕРХНИЙ УЗЕЛ %d", Node.UP.lvl);
				Node.UP.NodeOut();*/
				//GT=Node.UP.DoubleGoalTest(rootResult);
				if(!GT)
					CreateTreeStart(Node.UP);
				else {
					System.out.printf("Пройдено шагов: %d", steps);
					try {mutex.wait();} catch (InterruptedException e) {e.printStackTrace();}}
			}
			/*else
				Node.UP = null;*/
			if(!GT && Node.DOWN != null /*&& !Search(Node.DOWN, ALStart)*/) {
				/*System.out.printf("\n *дерево 1* НИЖНИЙ УЗЕЛ %d", Node.DOWN.lvl);
				Node.DOWN.NodeOut();*/
				//GT=Node.DOWN.DoubleGoalTest(rootResult);
				if(!GT)
					CreateTreeStart(Node.DOWN);
				else {
					System.out.printf("Пройдено шагов: %d", steps);
					try {mutex.wait();} catch (InterruptedException e) {e.printStackTrace();}}
			}
			/*else 
				Node.DOWN = null;*/	
		}
	}
	
	void CreateTreeResult(Node Node) {
		mutex.notifyAll();
		try {mutex.wait();} catch (InterruptedException e) {e.printStackTrace();}
		if(Node != null) {
			Node.LEFT();
			Node.RIGHT();
			Node.UP();
			Node.DOWN();
			Node.makeOpened();
			if (ch == 0) {
				System.out.printf("Выбран пошаговый режим. Сменить режим? (1 - да, 0 - нет): ");
				ch = in.nextInt();
				steps++;
				System.out.printf("\n *дерево 2* Раскрываемая вершина. Мы на уровне %d", Node.lvl);
				Node.NodeOut();
				System.out.printf("\nРаскрытые узлы: ");
				if(Node.LEFT != null) {Node.LEFT.NodeOut(); GT = Node.LEFT.DoubleGoalTest(rootStart, steps);}
				if(Node.RIGHT != null && !GT) {Node.RIGHT.NodeOut(); GT = Node.RIGHT.DoubleGoalTest(rootStart, steps);}
				if(Node.UP != null && !GT) {Node.UP.NodeOut(); GT = Node.UP.DoubleGoalTest(rootStart, steps);}
				if(Node.DOWN != null && !GT) {Node.DOWN.NodeOut(); GT = Node.DOWN.DoubleGoalTest(rootStart, steps);}
				if(!GT) System.out.printf("\nВыявленные повторы: ");
				if(Node.LEFT != null && !GT)
					if(Search(Node.LEFT, ALResult)) {
						Node.LEFT.NodeOut();
						Node.LEFT = null;
					}
				if(Node.RIGHT != null && !GT)
					if(Search(Node.RIGHT, ALResult)) {
						Node.RIGHT.NodeOut();
						Node.RIGHT = null;
					}
				if(Node.UP != null && !GT)
					if(Search(Node.UP, ALResult)) {
						Node.UP.NodeOut();
						Node.UP = null;
					}
				if(Node.DOWN != null && !GT)
					if(Search(Node.DOWN, ALResult)) {
						Node.DOWN.NodeOut();
						Node.DOWN = null;
					}
				if(!GT) {
					System.out.printf("\nКайма: ");
					OutEnds(rootResult);
				}
			}
			else {
				steps++;
				System.out.printf("\n *дерево 2* Раскрываемая вершина. Мы на уровне %d", Node.lvl);
				Node.NodeOut();
				System.out.printf("\nРаскрытые узлы: ");
				if(Node.LEFT != null) {Node.LEFT.NodeOut(); GT = Node.LEFT.DoubleGoalTest(rootStart, steps);}
				if(Node.RIGHT != null && !GT) {Node.RIGHT.NodeOut(); GT = Node.RIGHT.DoubleGoalTest(rootStart, steps);}
				if(Node.UP != null && !GT) {Node.UP.NodeOut(); GT = Node.UP.DoubleGoalTest(rootStart, steps);}
				if(Node.DOWN != null && !GT) {Node.DOWN.NodeOut(); GT = Node.DOWN.DoubleGoalTest(rootStart, steps);}
				if(!GT) System.out.printf("\nВыявленные повторы: ");
				if(Node.LEFT != null && !GT)
					if(Search(Node.LEFT, ALResult)) {
						Node.LEFT.NodeOut();
						Node.LEFT = null;
					}
				if(Node.RIGHT != null  && !GT)
					if(Search(Node.RIGHT, ALResult)) {
						Node.RIGHT.NodeOut();
						Node.RIGHT = null;
					}
				if(Node.UP != null  && !GT)
					if(Search(Node.UP, ALResult)) {
						Node.UP.NodeOut();
						Node.UP = null;
					}
				if(Node.DOWN != null  && !GT)
					if(Search(Node.DOWN, ALResult)) {
						Node.DOWN.NodeOut();
						Node.DOWN = null;
					}
				if(!GT) {
					System.out.printf("\nКайма: ");
					OutEnds(rootResult);
				}
			}
			//GT=Node.DoubleGoalTest(rootStart);
			if (!GT && Node.LEFT != null /*&& !Search(Node.LEFT, ALResult)*/) {
				/*System.out.printf("\n *дерево 2* ЛЕВЫЙ УЗЕЛ %d", Node.LEFT.lvl);
				Node.LEFT.NodeOut();*/
				//GT=Node.LEFT.DoubleGoalTest(rootStart);
				if(!GT)
					CreateTreeResult(Node.LEFT);
				else {
					System.out.printf("Пройдено шагов: %d", steps);
					try {mutex.wait();} catch (InterruptedException e) {e.printStackTrace();}}
				}
			/*else
				Node.LEFT = null;*/
			if(!GT && Node.RIGHT != null /*&& !Search(Node.RIGHT, ALResult)*/) {
				/*System.out.printf("\n *дерево 2* ПРАВЫЙ УЗЕЛ %d", Node.RIGHT.lvl);
				Node.RIGHT.NodeOut();*/
				//GT=Node.RIGHT.DoubleGoalTest(rootStart);
				if(!GT)
					CreateTreeResult(Node.RIGHT);
				else {
					System.out.printf("Пройдено шагов: %d", steps);
					try {mutex.wait();} catch (InterruptedException e) {e.printStackTrace();}}
			}
			/*else
				Node.RIGHT = null;*/
			if(!GT && Node.UP != null /*&& !Search(Node.UP, ALResult)*/) {
				/*System.out.printf("\n *дерево 2* ВЕРХНИЙ УЗЕЛ %d", Node.UP.lvl);
				Node.UP.NodeOut();*/
				//GT=Node.UP.DoubleGoalTest(rootStart);
				if(!GT)
					CreateTreeResult(Node.UP);
				else {
					System.out.printf("Пройдено шагов: %d", steps);
					try {mutex.wait();} catch (InterruptedException e) {e.printStackTrace();}}
			}
			/*else
				Node.UP = null;*/
			if(!GT && Node.DOWN != null /*&& !Search(Node.DOWN, ALResult)*/) {
				/*System.out.printf("\n *дерево 2* НИЖНИЙ УЗЕЛ %d", Node.DOWN.lvl);
				Node.DOWN.NodeOut();*/
				//GT=Node.DOWN.DoubleGoalTest(rootStart);
				if(!GT)
					CreateTreeResult(Node.DOWN);
				else {
					System.out.printf("Пройдено шагов: %d", steps);
					try {mutex.wait();} catch (InterruptedException e) {e.printStackTrace();}}
			}
			/*else 
				Node.DOWN = null;*/	
		}
	}
}

public class MAIN {
	public static void main(String arts[]) {
		int ch;
		Scanner in = new Scanner(System.in);
		System.out.printf("Каким поиском воспользоваться? (0 - в глубину, 1 - двунаправленный, 2 - А*): ");
		ch = in.nextInt();
		if(ch == 0) {
			Depth Depth;
			Tree Tree;
			Tree = new Tree();
			Depth = new Depth(Tree.root);
			Depth.CreateTree(Tree.root);
		}
		if(ch == 1) {
			DoubleSearch DoubleSearch;
			TreeResult TreeResult;
			TreeResult = new TreeResult();
			DoubleSearch = new DoubleSearch(TreeResult.rootStart, TreeResult.rootResult);
			DoubleSearch.start();
		}
		if(ch == 2) {
			AStar AStar;
			Tree Tree;
			Tree = new Tree();
			AStar = new AStar(Tree);
			AStar.Start();
		}
	}
}