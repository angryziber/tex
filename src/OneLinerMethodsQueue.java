public class OneLinerMethodsQueue implements Queue {
  private static final Item EMPTY = new EmptyItem();
  private Item top = EMPTY;

  @Override public boolean isEmpty() {
    return top.isEmpty();
  }

  @Override public int size() {
    return top.size();
  }

  @Override public void add(Object data) {
    top.insertTo(new TopItemReceiver(), data);
  }

  @Override public Object top() {
    return top.getData();
  }

  @Override public void remove() {
    top = top.getNext();
  }

  static class Item {
    private Item next = EMPTY;
    private Object data;

    Item(Object data) {
      this.data = data;
    }

    boolean isEmpty() {
      return data == null;
    }

    Object getData() {
      return data;
    }

    int size() {
      return 1 + next.size();
    }

    Item getNext() {
      return next;
    }

    void insertTo(Item receiver, Object data) {
      next.insertTo(this, data);
    }

    void setNext(Item next) {
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

    @Override public void insertTo(Item receiver, Object data) {
      receiver.setNext(new Item(data));
    }
  }

  class TopItemReceiver extends EmptyItem {
    @Override public void setNext(Item next) {
      top = next;
    }
  }
}
