public class CraftedQueue implements Queue {
  private Object item;
  private CraftedQueue next;

  @Override
  public boolean isEmpty() {
    return item == null;
  }

  @Override
  public int size() {
    return countFrom(this);
  }

  private static int countFrom(CraftedQueue queue) {
    return queue.item == null ? 0 : 1 + countFrom(queue.next);
  }

  @Override
  public void add(Object item) {
    addToTail(this, item);
  }

  private static void addToTail(CraftedQueue queue, Object item) {
    if (queue.item == null) {
      queue.item = item;
      queue.next = new CraftedQueue();
    }
    else addToTail(queue.next, item);
  }

  @Override
  public Object top() {
    if (item == null) throw new AssertionError();
    return item;
  }

  @Override
  public void remove() {
    if (next == null) throw new AssertionError();
    item = next.item;
    next = next.next;
  }
}
