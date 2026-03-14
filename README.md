# CSCMatch – Java Matchmaking System

CSCMatch is a console-based Java application that helps manage member profiles and generate compatibility matches based on shared interests.

## Features
- Add, remove, and list members.
- Track member interests with levels (0–10).
- Compute compatibility scores between members.
- Display top 5 matches for any member.
- Save and load membership data using file serialization.
- Prevent accidental data loss with unsaved changes warnings.

## Technologies
- Java (OOP, Serializable, Comparable)
- Custom LinkedOrderedList and LinkedUnorderedList data structures
- File I/O and Iterators

## Usage
1. Run `CSCMatch.java`.
2. Navigate the menu:
   - Load or save members
   - Add or remove members
   - Add interests to members
   - View member profiles and top matches
3. Follow prompts for input.

## Notes
- Compatibility is calculated using shared interest levels.
- Interests only the other member has are partially counted.
- Top matches are sorted by score descending.
