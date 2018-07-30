package Q1;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class DoubleHashingMap<K, V> implements Map<K, V> {
    // hashi(x) = (hash(x) + f(i)) % ts
    // f(i) = i * hash2(x)
    // hash2(x) = R - ( x % R)
    private HashNode<K, V>[] table;
    private int count = 0;
    private final int R = 7;
    private int INITIALSIZE = 23;
    private final double THRESHOLD = 0.75;

    public DoubleHashingMap() {

        table = new HashNode[INITIALSIZE];
    }
    public DoubleHashingMap(int initialCapacity) {

        INITIALSIZE = getNewTableSize(initialCapacity/2); // initialCapacitiyden büyük ilk prime number seçiliyor

        table = new HashNode[INITIALSIZE];
    }
    @Override
    public int size() {

        return count;
    }

    @Override
    public boolean isEmpty() {

        return count == 0;
    }

    @Override
    public boolean containsKey(Object key) {

        int i = 0;
        int hashindex;
        HashNode<K, V> current;
        int pozitifhascode = key.hashCode() % Integer.MAX_VALUE;
        while (true) {
            hashindex = getHashindex(pozitifhascode, i);
            current = table[hashindex];

            if (current == null) {
                return false;
            } else if (current.state == NodeState.ACTIVE) {
                if (current.entry.getKey().equals(key)) {
                    return true;
                }
            }
            i++;
        }
        //return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                if (table[i].state == NodeState.ACTIVE && table[i].entry.getValue().equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {

        int i = 0;
        int hashindex;
        HashNode<K, V> current;
        HashNode<K, V> newnode;
        int pozitifhascode = key.hashCode() % Integer.MAX_VALUE;
        while (true) {
            hashindex = getHashindex(pozitifhascode, i);
            current = table[hashindex];

            if (current == null) {
                return null;
            } else if (current.state == NodeState.ACTIVE) {
                if (current.entry.getKey().equals(key)) {
                    return current.entry.getValue();
                }
            }
            i++;
        }
        // return null;
    }

    @Override
    public V put(K key, V value) {
        ifNecessaryEnlargeHashTable();

        int i = 0;
        int hashindex;
        HashNode<K, V> current;
        HashNode<K, V> newnode;
        int pozitifhascode = key.hashCode() % Integer.MAX_VALUE;
        while (true) {
            hashindex = getHashindex(pozitifhascode, i);
            current = table[hashindex];

            if (current == null) {
                newnode = new HashNode<>(new HashEntry<>(key, value));
                table[hashindex] = newnode;
                count++;
                return value;
            } else if (current.state == NodeState.ACTIVE) {
                if (current.entry.getKey().equals(key)) {
                    current.entry.setValue(value);
                    return value;
                }
            } else if (current.state == NodeState.DELETED) {
                current.state = NodeState.ACTIVE;
                current.setEntry(new HashEntry<>(key, value));
                count++;
                return value;
            }
            i++;
        }

        //return null;

    }

    private void ifNecessaryEnlargeHashTable() {
        double FullnessRate = (double) count / table.length;
        // Hasttable ın doluluk oranı threshold u aşmış ise hashtable 2 kat kadar büyütülüyor
        if (FullnessRate > THRESHOLD) {
            HashNode<K, V>[] oldtable = table;
            int newsize = getNewTableSize(oldtable.length);
            table = new HashNode[newsize];
            K key;
            //  Eski hashtable daki değerler yeni hashtabla konuluyor
            for (int k = 0; k < oldtable.length; k++) {
                if (oldtable[k] != null && oldtable[k].state == NodeState.ACTIVE) {
                    key = oldtable[k].entry.getKey();
                    int i = 0;
                    int hashindex;
                    HashNode<K, V> current;
                    HashNode<K, V> oldnode = oldtable[k];
                    int pozitifhascode = key.hashCode() % Integer.MAX_VALUE;
                    while (true) {
                        hashindex = getHashindex(pozitifhascode, i);
                        current = table[hashindex];

                        if (current == null) {
                            table[hashindex] = oldnode;
                            break;
                        }
                        i++;
                    }
                }
            }
        }

    }

    private int getNewTableSize(int currentsize) {
        // Hashtableın kapasitesi 2 kadar kadar büyük olacak şekilde bir asal sayı seçiliyor
        int newsize = currentsize * 2 + 1;

        while (!isPrime(newsize)) {
            newsize = newsize + 2;
        }
        return newsize;
    }

    private static boolean isPrime(int number) {
        if (number < 2) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        for (int i = 3; i * i <= number; i = i + 2)
            if (number % i == 0) return false;
        return true;
    }

    private int getHashindex(int code, int i) {

        return (code % table.length + i * hash2(code)) % table.length;
    }

    private int hash2(int code) {

        return this.R - code % this.R;
    }

    @Override
    public V remove(Object key) {

        int i = 0;
        int hashindex;
        HashNode<K, V> current;
        int pozitifhascode = key.hashCode() % Integer.MAX_VALUE;
        while (true) {
            hashindex = getHashindex(pozitifhascode, i);
            current = table[hashindex];

            if (current == null) {
                return null;
            } else if (current.state == NodeState.ACTIVE) {
                if (current.entry.getKey().equals(key)) {
                    current.setState(NodeState.DELETED);
                    return current.entry.getValue();
                }
            }
            i++;
        }

        //return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

        for (Entry<? extends K, ? extends V> item : m.entrySet()) {
            put(item.getKey(), item.getValue());
        }
    }

    @Override
    public void clear() {

        table = new HashNode[INITIALSIZE];
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].state == NodeState.ACTIVE) {
                set.add(table[i].entry.getKey());
            }

        }
        return set;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> list = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                list.add(table[i].entry.getValue());
            }
        }

        return list;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {

        Set<Entry<K, V>> set = new HashSet<>();

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].state == NodeState.ACTIVE) {
                set.add(table[i].entry);
            }
        }


        return set;
    }


    private static enum NodeState {
        ACTIVE,
        DELETED

    }

    private static class HashNode<K, V> {
        private Entry<K, V> entry;
        private NodeState state;

        public HashNode(Entry<K, V> entry) {
            this.entry = entry;
            this.state = NodeState.ACTIVE;
        }

        public Entry<K, V> getEntry() {
            return entry;
        }

        public void setEntry(Entry<K, V> entry) {
            this.entry = entry;
        }

        public NodeState getState() {
            return state;
        }

        public void setState(NodeState state) {
            this.state = state;
        }
    }

    private static class HashEntry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return this.value;
        }
    }

    // Hashtable için test amaçlı görmek için yazıldı
    public void printHashTable() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                System.out.print("null ");
            } else if (table[i].state == NodeState.DELETED) {
                System.out.print("DELETED ");
            } else if (table[i].state == NodeState.ACTIVE) {
                System.out.print(table[i].entry.getKey() + " ");

            }
        }
        System.out.println();
    }

}
