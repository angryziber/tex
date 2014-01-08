public class CraftedQueue implements Queue {
  private Item top = new EmptyItem();

  @Override public boolean isEmpty() {
    return top.isEmpty();
  }

  @Override public int size() {
    return top.size();
  }

  @Override public void add(Object data) {
    top.setToTail(new TopItem(), data);
  }

  @Override public Object top() {
    return top.getData();
  }

  @Override public void remove() {
    top = top.getNext();
  }

  static abstract class Item {
    Object data;

    Item(Object data) {
      this.data = data;
    }

    public Object getData() {
      return data;
    }

    public boolean isEmpty() {
      return data == null;
    }

    abstract Item getNext();

    abstract int size();

    abstract void setToTail(Item parent, Object data);

    void replaceNext(Item next) {
    }
  }

  static class FilledItem extends Item {
    Item next = new EmptyItem();

    FilledItem(Object data) {
      super(data);
    }

    public int size() {
      return 1 + next.size();
    }

    public Item getNext() {
      return next;
    }

    public void setToTail(Item parent, Object data) {
      next.setToTail(this, data);
    }

    public void replaceNext(Item next) {
      this.next = next;
    }
  }

  static class EmptyItem extends Item {
    EmptyItem() {
      super(null);
    }

    @Override public int size() {
      return 0;
    }

    @Override public Object getData() {
      throw new AssertionError();
    }

    @Override public Item getNext() {
      throw new AssertionError();
    }

    @Override public void setToTail(Item parent, Object data) {
      parent.replaceNext(new FilledItem(data));
    }
  }

  class TopItem extends EmptyItem {
    @Override public void replaceNext(Item next) {
      top = next;
    }
  }
}
