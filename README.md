# cpsc39-finalProjects

Upload your final project to this github repo.

Make a README file for your project and put the infor about your project in it - your name, date and what your program does.

Jose Tapia Lopez

07/18/2025

Final programming project- Uno game

The goal is to make a terminal based on the phycial card game Uno where the user can draw cards, place cards, 
sort yoru deck of cards, shuffle before every new game starts and assign a point system within to see who is winning. 


 /**
     * Sorts the player's hand by card number using selection sort.
     * Data Structure: ArrayList used to store the player's hand.
     * Sorting Algorithm: Selection Sort
     */
    public void sortHandByNumber() {
        // --- Selection Sort Algorithm ---
        for (int i = 0; i < hand.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(j).getNumber() < hand.get(minIndex).getNumber()) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            if (minIndex != i) {
                UnoColorSelector.Card temp = hand.get(i);
                hand.set(i, hand.get(minIndex));
                hand.set(minIndex, temp);
            }
        }
    }



/**
     * Sorts the player's hand by color, then by number using merge sort.
     * Data Structure: ArrayList used to store the player's hand.
     * Sorting Algorithm: Merge Sort
     */
    public void sortHandByColorThenNumber() {
        if (hand.size() <= 1) return;
        handMergeSort(0, hand.size() - 1);
    }

    // --- Merge Sort Algorithm ---
    private void handMergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            handMergeSort(left, mid);
            handMergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right) {
        List<UnoColorSelector.Card> temp = new ArrayList<>();
        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            UnoColorSelector.Card cardI = hand.get(i);
            UnoColorSelector.Card cardJ = hand.get(j);
            // Compare by color first, then by number if colors are equal
            if (cardI.getColor().ordinal() < cardJ.getColor().ordinal() ||
                (cardI.getColor() == cardJ.getColor() && cardI.getNumber() <= cardJ.getNumber())) {
                temp.add(cardI);
                i++;
            } else {
                temp.add(cardJ);
                j++;
            }
        }
        while (i <= mid) {
            temp.add(hand.get(i));
            i++;
        }
        while (j <= right) {
            temp.add(hand.get(j));
            j++;
        }
        // Copy sorted temp list back into hand
        for (int k = 0; k < temp.size(); k++) {
            hand.set(left + k, temp.get(k));
        }
    }



/**
     * Sorts the player's hand by color only using insertion sort.
     */
    public void sortHandByColor() {
        for (int i = 1; i < hand.size(); i++) {
            UnoColorSelector.Card key = hand.get(i);
            int j = i - 1;
            while (j >= 0 && hand.get(j).getColor().ordinal() > key.getColor().ordinal()) {
                hand.set(j + 1, hand.get(j));
                j--;
            }
            hand.set(j + 1, key);
        }
    }

Implemented color & number seperate as once gave the numbers and color some issues as where they shoudl go position wise so the additional helped sort the players hands into the correct place.