#include <string>
#include <unordered_map>
#include <iostream>

std::string duplicate_encoder(const std::string& word){
    // Hashmap to see if a character has been seen before
    std::unordered_map<char, int> seen;

    for (int i = 0; i < (int) word.length(); i++) {
        // If the character ignoring case has been seen before, increment the value
        if (seen.find(tolower(word[i])) != seen.end()) {
            seen[tolower(word[i])]++;
        } else {
            // Otherwise, add the character to the hashmap
            seen[tolower(word[i])] = 1;
        }
    }

    std::string result = "";

    for (int i = 0; i < (int) word.length(); i++) {
        // If the character has been seen more than once, add a )
        if (seen[tolower(word[i])] > 1) {
            result += ")";
        } else {
            // Otherwise, add a (
            result += "(";
        }
    }

    return result;
}