public class CraftedQueue implements Queue {
  private Link top = new Link();

  @Override
  public boolean isEmpty() {
    return top.data == null;
  }

  @Override
  public int size() {
    return countFrom(top);
  }

  private int countFrom(Link link) {
    return link.data == null ? 0 : 1 + countFrom(link.next);
  }

  @Override
  public void add(Object item) {
    addToTail(top, item);
  }

  private void addToTail(Link link, Object item) {
    if (link.data == null) {
      link.data = item;
      link.next = new Link();
    }
    else addToTail(link.next, item);
  }

  @Override
  public Object top() {
    if (top.data == null) throw new AssertionError();
    return top.data;
  }

  @Override
  public void remove() {
    if (top.next == null) throw new AssertionError();
    top = top.next;
  }

  class Link {
    Object data;
    Link next;
  }
}
