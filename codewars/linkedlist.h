//
// Created by arsyad on 8/12/2022.
//

#ifndef CODEWARS_LINKEDLIST_H
#define CODEWARS_LINKEDLIST_H

#endif //CODEWARS_LINKEDLIST_H

#include <stdlib.h>

// Node struct
typedef struct node {
    int data;
    struct node *next;
} node_t;


// Function to create a new node
node_t *create_node(int data) {
    node_t *new_node = malloc(sizeof(node_t));
    new_node->data = data;
    new_node->next = NULL;
    return new_node;
}

// Function to create a circular linked list from an array
node_t *create_circular_linked_list(int *arr, int n) {
    node_t *head = create_node(arr[0]);
    node_t *current = head;
    for (int i = 1; i < n; i++) {
        current->next = create_node(arr[i]);
        current = current->next;
    }
    current->next = head;
    return head;
}

// Function to remove next node from a circular linked list
void remove_next_node(node_t *node) {
    node_t *next_node = node->next;
    node->next = next_node->next;
    free(next_node);
}