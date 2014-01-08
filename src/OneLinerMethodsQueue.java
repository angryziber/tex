public class OneLinerMethodsQueue implements Queue {
  private Item top = new EmptyItem();

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

  static abstract class Item {
    private Object data;

    Item(Object data) {
      this.data = data;
    }

    public boolean isEmpty() {
      return data == null;
    }

    public Object getData() {
      return data;
    }

    abstract int size();
    abstract Item getNext();
    abstract void insertTo(Item receiver, Object data);
    abstract void setNext(Item next);
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

    @Override public void insertTo(Item receiver, Object data) {
      next.insertTo(this, data);
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

    @Override public void insertTo(Item receiver, Object data) {
      receiver.setNext(new FilledItem(data));
    }

    @Override void setNext(Item next) {
      throw new UnsupportedOperationException();
    }
  }

  class TopItemReceiver extends EmptyItem {
    @Override public void setNext(Item next) {
      top = next;
    }
  }
}
