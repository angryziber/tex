import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {
  OneLinerMethodsQueue queue = new OneLinerMethodsQueue();

  @Test
  public void empty() {    
    assertTrue(queue.isEmpty());
    assertEquals(0, queue.size());
  }

  @Test
  public void one() {
    queue.add("a");
    assertFalse(queue.isEmpty());
    assertEquals(1, queue.size());
    assertEquals("a", queue.top());
  }

  @Test
  public void two() {
    queue.add("a");
    queue.add("b");
    assertFalse(queue.isEmpty());
    assertEquals(2, queue.size());
    assertEquals("a", queue.top());
  }

  @Test
  public void remove() {
    queue.add("a");
    queue.add("b");

    queue.remove();
    assertEquals("b", queue.top());
    assertEquals(1, queue.size());

    queue.remove();
    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());
  }

  @Test(expected = AssertionError.class)
  public void emptyQueueTopFails() {
    queue.top();
  }

  @Test(expected = AssertionError.class)
  public void emptyQueueRemoveFails() {
    queue.remove();
  }

  @Test
  public void removeRestoresEmptyState() {
    queue.add("c");
    queue.remove();
    empty();
  }
}
