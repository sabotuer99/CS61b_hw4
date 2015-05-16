// Make sure to make this class a part of the synthesizer package
package synthesizer;

public class ArrayRingBuffer extends AbstractBoundedQueue {
  /* Index for the next dequeue or peek. */
  private int first;           
  /* Index for the next enqueue. */
  private int last;             
  /* Array for storing the buffer data. */
  private double[] rb;

  /** Create a new ArrayRingBuffer with the given capacity. */
  public ArrayRingBuffer(int capacity) {
    // TODO: Create new array with capacity elements.
    //       first, last, and fillCount should all be set to 0. 
    //       this.capacity should be set appropriately. Note that the local variable
    //       here shadows the field we inherit from AbstractBoundedQueue.
	  rb = new double[capacity];
	  this.capacity = capacity;
	  first = 0;
	  last = 0;
	  fillCount = 0;		  
  }

  /** Adds x to the end of the ring buffer. If there is no room, then
    * throw new RuntimeException("Ring buffer overflow") 
    */
  public void enqueue(double x) {
    // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    // is there room?
	  if(fillCount == capacity)
		  throw new RuntimeException("Buffer is full!");
	  
	  //if buffer is not empty, advance last
	  if(!this.isEmpty())
		  last = advance(last);
	  
	  rb[last] = x;
	  fillCount += 1;
		  
  }

  /** Dequeue oldest item in the ring buffer. If the buffer is empty, then
    * throw new RuntimeException("Ring buffer underflow");
    */
  public double dequeue() {
    // TODO: Dequeue the first item. Don't forget to decrease fillCount and update first.
	  if(fillCount == 0)
		  throw new RuntimeException("Can't dequeue empty buffer");
	  
	  double value = rb[first];
	  first = advance(first);
	  fillCount -= 1;
	  
	  return value;
  }

  /** Return oldest item, but don't remove it. */
  public double peek() {
    // TODO: Return the first item. None of your instance variables should change.
	  if(fillCount == 0)
		  throw new RuntimeException("Can't peek empty buffer");
	  
	  return rb[first];
  }

  private int advance(int marker){
	  //if marker is at the end of the array, loop around
	  if(marker == rb.length -1){
		  marker = 0;
	  } else {
		  marker += 1;
	  }
	  
	  return marker;
  }
}
