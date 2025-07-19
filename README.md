# cpsc39-finalProjects

Upload your final project to this github repo.

Make a README file for your project and put the infor about your project in it - your name, date and what your program does.

Jose Tapia Lopez

07/18/2025

Final programming project- Uno game

The goal is to make a terminal based on the phycial card game Uno where the user can draw cards, place cards, 
sort yoru deck of cards, shuffle before every new game starts and assign a point system within to see who is winning. Additionally have a special card called the cat card.


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
Final Project Report

To begin for the final programming assignment I chose was to create the well know game called Uno, where there is a set amount of players and the objectitve is to be the first one to reach/ call out 'Uno' indicating your one card away from winning as the requirement is to have no cards left before any of the other players. As I was younger, I found this game entertaining to play, so the special twist I'd like to do is include a special card called 'Cat', where the result of getting this cat card is to switch out one of your cards for a fresh card that is on top of the stack of new cards within.

Within the project, I decided to use the insertion, selection, and merge sort algorithms as I feel the most comfortable and familiar with the concept that was shown with LinkedIn Learning/ Zybooks. 

{Images of the flowcharts attached separately}

By incorporating these 3 selected algorithms, I was able to initially set an order for my players' cards, which is done by the use of color and the numbers to help solidify the sorting process. Initially, with both separate, I raised some concerns. Incorporating merge sort fixed the issue I had been having for a while, coming to the sorting of the players' cards within their hands. During the creation, I used some ChatGPT for the initial structure, but went to Zybooks and linkedin learning to help me strengthen my understanding of how I'd be able to incorporate within the Uno game. As I learned throughout the course, these algorithms I chose typically have a Big-Oh(N^2), which isn't the best time complexity, but is able to help me with my selection process when the arrays aren't of great length, and sometimes be prepared as a nearly sorted array. Continuing on the selection choices, I included an ArrayList to hold the Player Name and the Hand they get whenever the game begins. While incorporating a linked list within my deck.java file for a faster opening when cards get chosen or placed within the game, as it's faster to access compared to using an ArrayList, when adding or taking away cards, since it's similar to a stack data structure. I also added a record, which at first I was too sure of, but saw it is an immutable data carrier. Which helps when assigning colors to the Uno decks within the deck, as the color with no change within the program, making it useful to implement within this Uno program.

Furthermore, during this experience, I was able to gain some opportunities by learning more about the data structures and algorithms I considered using and used. Such as stacks, records, dequeues, and a few sorting algorithms throughout this experience, which was a great opportunity to branch out from the typical format of the course and how they could be applied within different scenarios, which helped me improve my knowledge when it came to being able to implement these data structures and future applications. But I did end up discovering several issues when it came to the construction of the Uno program, as I found it somewhat hard to get the hands to show from the players and add the feature of playing and adding a card to the user deck. I went through maybe trials and error by using stacks, for loops, and while loops to help me see where the best fit would be when it came to this specific issue. While also trying to implement the color of the cards, as I was familiar with how and what a record was during the process of this. 

I think what I would do differently for this application is make a GUI for this program, as it's terminal-based right now, which does take some of the appeal away since there is only a command line to play this game within. But also, I think I'd like to add more special cards and a choice to peek at the other player's cards as well. I think this would be a cool feature.