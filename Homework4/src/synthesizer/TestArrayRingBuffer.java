package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
	private static final double DELTA = 1e-15;
	
    @Test
    public void Constructor_GivenCapacityOne_CreatesNewBuffer() {
    	//Arrange
        // nothing to do
        
        //Act
    	ArrayRingBuffer arb = new ArrayRingBuffer(1);
        
        //Assert
    	assertEquals(1, arb.capacity());
    	assertEquals(0, arb.fillCount());
    	assertTrue(arb.isEmpty());
    }
    
    @Test(expected=RuntimeException.class)
    public void Peek_EmptyBuffer_ThrowsRuntimeException() {
    	//Arrange
    	ArrayRingBuffer arb = new ArrayRingBuffer(1);
        
        //Act
    	arb.peek();
        
        //Assert
    	//nothing to do
    }
    
    @Test(expected=RuntimeException.class)
    public void Dequeue_EmptyBuffer_ThrowsRuntimeException() {
    	//Arrange
    	ArrayRingBuffer arb = new ArrayRingBuffer(1);
        
        //Act
    	arb.dequeue();
        
        //Assert
    	//nothing to do
    }
    
    @Test(expected=RuntimeException.class)
    public void Enqueue_FullBuffer_ThrowsRuntimeException() {
    	//Arrange
    	ArrayRingBuffer arb = new ArrayRingBuffer(1);
    	arb.enqueue(1.0);
        
        //Act
    	arb.enqueue(1.0);
        
        //Assert
    	//nothing to do
    }
    
    @Test
    public void Enqueue_EmptyBuffer_AddsToBuffer() {
    	//Arrange
    	ArrayRingBuffer arb = new ArrayRingBuffer(4);
        
        //Act
    	arb.enqueue(1.0);
        
        //Assert
    	assertEquals(1, arb.fillCount());
    }
    
    @Test
    public void Enqueue_FillBuffer_isFullTrue() {
    	//Arrange
    	ArrayRingBuffer arb = new ArrayRingBuffer(4);
        
        //Act
    	arb.enqueue(1.0);
    	arb.enqueue(1.0);
    	arb.enqueue(1.0);
    	arb.enqueue(1.0);
        
        //Assert
    	assertTrue(arb.isFull());
    }
    
    @Test
    public void Dequeue_EmptyBuffer_isEmptyTrue() {
    	//Arrange
    	ArrayRingBuffer arb = new ArrayRingBuffer(4);
    	arb.enqueue(1.0);
    	arb.enqueue(1.0);
    	arb.enqueue(1.0);
    	arb.enqueue(1.0);
    	
        //Act
    	arb.dequeue();
    	arb.dequeue();
    	arb.dequeue();
    	arb.dequeue();
        
        //Assert
    	assertTrue(arb.isEmpty());
    }
    
    @Test
    public void Dequeue_DataInBuffer_ReturnsOldestToNewest() {
    	//Arrange
    	ArrayRingBuffer arb = new ArrayRingBuffer(4);
    	arb.enqueue(1.0);
    	arb.enqueue(2.0);
    	arb.enqueue(3.0);
    	arb.enqueue(4.0);
    	
        //Act
    	double first = arb.dequeue();
    	double second = arb.dequeue();
    	double third = arb.dequeue();
    	double fourth = arb.dequeue();
        
        //Assert
    	assertEquals(1.0, first, DELTA);
    	assertEquals(2.0, second, DELTA);
    	assertEquals(3.0, third, DELTA);
    	assertEquals(4.0, fourth, DELTA);

    }
    
    @Test
    public void Peek_DataInBuffer_ReturnsOldestWithoutEffectingArray() {
    	//Arrange
    	ArrayRingBuffer arb = new ArrayRingBuffer(4);
    	arb.enqueue(1.0);
    	arb.enqueue(2.0);
    	arb.enqueue(3.0);
    	arb.enqueue(4.0);
    	
        //Act
    	double peek = arb.peek();
    	double first = arb.dequeue();
        
        //Assert
    	assertEquals(1.0, peek, DELTA);
    	assertEquals(1.0, first, DELTA);

    }
    
    @Test
    public void Enqueue_MultipleEnquesAndDequeues_LoopsAndContinuesFunctioning() {
    	//Arrange
    	ArrayRingBuffer arb = new ArrayRingBuffer(4);
    	arb.enqueue(1.0);
    	arb.enqueue(2.0);
    	arb.enqueue(3.0);
    	arb.enqueue(4.0); //at this point, buffer full
    	arb.dequeue();    //dequeue 1, rb[0] empty
    	arb.dequeue();    //dequeue 2, rb[1] empty
    	arb.enqueue(5.0); //enqueued in rb[0]
    	arb.enqueue(6.0); //enqueued in rb[1]
    	
        //Act
    	double first = arb.dequeue();
    	double second = arb.dequeue();
    	double third = arb.dequeue();
    	double fourth = arb.dequeue();
        
        //Assert
    	assertEquals(3.0, first, DELTA);
    	assertEquals(4.0, second, DELTA);
    	assertEquals(5.0, third, DELTA);
    	assertEquals(6.0, fourth, DELTA);

    }

    @Test
    public void FillCount_MultipleEnquesAndDequeues_ReturnsCountOfRemaining() {
    	//Arrange
    	ArrayRingBuffer arb = new ArrayRingBuffer(4);
    	arb.enqueue(1.0);
    	arb.dequeue();  
    	arb.enqueue(2.0);
    	arb.dequeue();  
    	arb.enqueue(3.0);
    	arb.dequeue();  
    	arb.enqueue(4.0); 
    	arb.dequeue();  
    	arb.enqueue(1.0);
    	arb.dequeue();  
    	arb.enqueue(2.0);
    	arb.dequeue();  
    	arb.enqueue(3.0);
    	arb.dequeue();  
    	arb.enqueue(4.0); 

    	
        //Act
    	int fillCount = arb.fillCount();
        
        //Assert
    	assertEquals(1, fillCount);

    }
    
    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 