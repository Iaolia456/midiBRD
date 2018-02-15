# midiBRD

midiBRD is an midi files to key stroke application. It translate notes event in midi files to keyboard presses which can be used to trigger skills placed on hotbars of the game [Final Fantasy XIV (FFXIV)](https://www.finalfantasyxiv.com/ "Final Fantasy XIV Homepage"). Its main objective is to take advantage of the performance feature of the game. This is the work in progress project.


## Updated to support patch 4.2
- Added support for new 4.2 _performance mode_ UI
- Temporary disable greeting emote function
- Make player's key presses a bit more accurate and more efficient

## Just any midi file?
There are some requirements of the midi file that midiBRD can read or _perform_ which listed below.
- Every note in the midi track have to be monophonic. Means that playing 2 or more notes (eg. chords) at the same time in the same midi track is not allowed.
- The midi file can have multiple tracks. You can tell midiBRD to performs every track in the file sequentially to enable the use of chords and harmony.
- Keep in mind that FFXIV has some delay after the player character performed a note. This means that the midi track that plays a sequence of notes really fast might not perform as expected in the game (wrong tempo or note skipping). Note that after patch 4.2, this delay was significantly reduced but **NOT** eliminated. 

## Shortcut key configuration for performance mode
The key combination used to play notes in performance mode needs to be configured as follow. This is because the midiBRD will press specific key combination to play a note in performance mode's UI.

**Please use the default key binding in keyboard mode**

## Demonstration
I have made a couple songs using midiBRD, please check it out here:
[midiBRD Youtube channel](https://www.youtube.com/channel/UC8bJVV_JO8bzOjbgcyzBrhQ)

## Additional Note
To play safe, **DO NOT USE THIS SOFTWARE TO PERFORMS ANY SONG THAT SQUARE ENIX DOES NOT OWN THE COPYRIGHT.** This means basically only perform Final Fantasy songs only. They are serious about copyright and stuff... We don't want to force them to remove this feature, right?
