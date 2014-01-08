interface Queue {
  boolean isEmpty();
  int size();
  void add(Object item);
  Object top();
  void remove();
}