# Iconzz
A small mod API allowing the easy use of custom icons in the game.

Currently a mod can call addIcon() in the preInit phase, and the icon will be loaded and everything will work perfectly.

The issue is that it generates a new pack each time the server is started, meaning unused packs willl slowly pile up on every player's disk.
