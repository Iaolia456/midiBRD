# midiBRD

midiBRD is an midi files to key stroke application. It translate notes event in midi files to keyboard presses which can be used to trigger a skill placed on hotbars of the game [Final Fantasy XIV (FFXIV)](https://www.finalfantasyxiv.com/ "Final Fantasy XIV Homepage"). Its main objective is to take advantage of the performance feature of the game. This is the work in progress project.


## Just any midi file?
There are some requirements of the midi file that midiBRD can read or _perform_ which listed below.
- Every note in the midi track have to be monophonic. Means that playing 2 or more notes (eg. chords) at the same time in the same midi track is not allowed.
- The midi file can have multiple tracks. You can tell midiBRD to performs every track in the file sequentially to enable the use of chords and harmony.
- Keep in mind that FFXIV has some delay after the player character performed a note. This means that the midi track that plays a sequence of notes really fast might not perform as expected in the game (wrong tempo or note skipping).

## FFXIV hotbar configuration
In FFXIV, there are 3 octaves plus one note available for the character to play. It start from **C(-1)** ranging to **C(+2)** as called in the game. You need 4 hotbars to place all notes for midiBRD to perform and they have to be configured as follow.

From left to right slot of the hotbar place...
- **The 1st bar**: the first octave starting from **C(-1)** and climb up the scale until **B(-1)**, including # and ♭ in the same row.
- **The 2nd bar**: the second octave starting from **C** and climb up the scale until **B**, including # and ♭ in the same row.
- **The 3rd bar**: the first octave starting from **C(+1)** and climb up the scale until **B(+1)**, including # and ♭ in the same row.
- **The 4th bar**: the lonely **C(+2)** note. The last slot of this bar can be any emote/skill, midiBRD will press this emote/skill automatically at the start and at the end of each midi track it performed _[WIP]_.

#### Shortcut key configuration
The key combination used to trigger the skill in the hotbar also has to be configured as follow. This is because the midiBRD will press specific key combination to trigger a skill (or a note) placed on the character's hotbar.
- **The 1st bar**: a **numeric key** row on top of the letters key.
- **The 2nd bar**: a **numeric key** row on top of the letters key plus **shift**.
- **The 3rd bar**: a **numeric key** row on top of the letters key plus **ctrl**.
- **The 4th bar**: a **numeric key** row on top of the letters key plus **alt**.

You can change the keyboard shortcut from within one of the FFXIV confusing option menus... Sorry I can't remember which option menu, they have too many...

## Additional Note
To play safe, **DO NOT USE THIS SOFTWARE TO PERFORMS ANY SONG THAT SQUARE ENIX DOES NOT OWN THE COPYRIGHT.** This means basically only perform Final Fantasy songs only. They are serious about copyright and stuff... We don't want to force them to remove this feature, right?
