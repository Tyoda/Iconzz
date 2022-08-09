# Iconzz
A small mod API allowing the easy use of custom icons in the game.

You need ago's server modlauncher for this to work.

NOTE: This mod will not be compatible with any other mods that use icons without using this library. However, the worst that should happen is either this or the other mod's icons will show up as weird, random icons. If you find such a mod, please report it here and we'll figure something out. Currently <a href="https://github.com/Arathok/Wurm-Unlimited-Alchemy">Arathok's Alchemy mod</a> (version 0.8.9) is known not to be compatible.
# Features
Add a new icon to the game by simply adding this mod as a library in your .properties file:

`depend.requires=Iconzz`

And adding one line in the code:

`short iconId = Iconzz.getInstance().addIcon("IconName", "mods/ExampleMod/icons/IconName.png");`

You can then use this ID while creating item templates to use the newly added icon:

`ItemTemplateBuilder exampleItem = new ItemTemplateBuilder("ExampleItem").imageNumber(iconId);`

For an example, see the <a href="https://github.com/Tyoda/IconzzExample">Example mod</a>

NOTE: The icon images must be 32x32, and the IconName in addIcon must be unique among all mods that use this library.

### Additional improvements to the icons in the game:
 - Huge Bell will now have an icon
 - Golden Mirror will now have an icon
 - Removed weird random lines on the borders of some icons
 - Removed default icon cube from behind the muffin icon lol
 - You can edit the images in the mod's resources folder to customize the game's icons to your liking!

# Credits
 - Many thanks to bdew for figuring out how to use serverpacks functions in the sharedClassLoader context, as seen in this <a href="https://gist.github.com/bdew/5ee9fcb39da73405a38810116104e101">TestMod</a>
