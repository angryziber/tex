public class CraftedQueue implements Queue {
  private Item top = new EmptyItem();

  @Override public boolean isEmpty() {
    return top.isEmpty();
  }

  @Override public int size() {
    return top.count();
  }

  @Override public void add(Object data) {
    top.setToTail(new TopItem(), data);
  }

  @Override public Object top() {
    return top.getData();
  }

  @Override public void remove() {
    top = top.remove();
  }

  interface Item {
    Object getData();
    boolean isEmpty();
    Item remove();
    int count();
    void setToTail(Item parent, Object data);
    void replaceNext(Item next);
  }

  static class FilledItem implements Item {
    Object data;
    Item next = new EmptyItem();

    FilledItem(Object data) {
      this.data = data;
    }

    public Object getData() {
      return data;
    }

    public boolean isEmpty() {
      return data == null;
    }

    public Item remove() {
      return next;
    }

    public int count() {
      return 1 + next.count();
    }

    public void setToTail(Item parent, Object data) {
      next.setToTail(this, data);
    }

    @Override
    public void replaceNext(Item next) {
      this.next = next;
    }
  }

  static class EmptyItem implements Item {
    @Override public boolean isEmpty() {
      return true;
    }

    @Override public int count() {
      return 0;
    }

    @Override public Object getData() {
      throw new AssertionError();
    }

    @Override public Item remove() {
      throw new AssertionError();
    }

    @Override public void setToTail(Item parent, Object data) {
      parent.replaceNext(new FilledItem(data));
    }

    @Override
    public void replaceNext(Item next) {
      throw new AssertionError();
    }
  }

  class TopItem extends EmptyItem {
    @Override public void replaceNext(Item next) {
      top = next;
    }
  }
}
