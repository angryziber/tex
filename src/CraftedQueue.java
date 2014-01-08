public class CraftedQueue implements Queue {
  private Item top = new EmptyItem();

  @Override public boolean isEmpty() {
    return top.isEmpty();
  }

  @Override public int size() {
    return top.size();
  }

  @Override public void add(Object data) {
    top.setData(data, new TopItem());
  }

  @Override public Object top() {
    return top.getData();
  }

  @Override public void remove() {
    top = top.getNext();
  }

  static abstract class Item {
    private Object data;

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

    abstract void setData(Object data, Item receiver);

    void setNext(Item next) {
      throw new UnsupportedOperationException();
    }
  }

  static class FilledItem extends Item {
    private Item next = new EmptyItem();

    FilledItem(Object data) {
      super(data);
    }

    @Override public int size() {
      return 1 + next.size();
    }

    @Override public Item getNext() {
      return next;
    }

    @Override public void setData(Object data, Item receiver) {
      next.setData(data, this);
    }

    @Override public void setNext(Item next) {
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

    @Override public void setData(Object data, Item receiver) {
      receiver.setNext(new FilledItem(data));
    }
  }

  class TopItem extends EmptyItem {
    @Override public void setNext(Item next) {
      top = next;
    }
  }
}
