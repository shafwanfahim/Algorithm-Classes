package solution;

public class Queue {
    public Vertex[] array;
    public int front;
    public int back;
    public int size;
    public int capacity;

    public Queue(int capacity) {

        this.capacity = capacity;
        this.array = new Vertex[capacity];
        this.front = 0;
        this.back = -1;
        this.size = 0;
    }

    public void enqueue(Vertex vertex) {
        if (size == capacity) {
            System.out.println("Queue is full. Cannot enqueue.");
            return;
        }
        back = (back + 1) % capacity;
        array[back] = vertex;
        size++;
    }

    public Vertex dequeue() {
        if (size == 0) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return null;
        }
        Vertex vertex = array[front];
        front = (front + 1) % capacity;
        size--;
        return vertex;
    }

}
