# Iconzz
A small mod API allowing the easy use of custom icons in the game.

You need ago's server modlauncher for this to work.

# Features
Add a new icon to the game by simply adding this mod as a library in your .properties file:

`depend.requires=Iconzz`

And adding one line in the code:

`short iconId = Iconzz.getInstance().addIcon("IconName", "mods/ExampleMod/icons/IconName.png");`

You can then use this ID while creating item templates to use the newly added icon:

`ItemTemplateBuilder exampleItem = new ItemTemplateBuilder("ExampleItem").imageNumber(iconId)`

For an example, see the <a href="https://github.com/Tyoda/IconzzExample">Example mod</a>

NOTE: The icon images must be 32x32, and the IconName in addIcon must be unique among all mods that use this library.

### Additional improvements to the icons in the game:
 - Huge Bell will now have an icon
 - Golden Mirror will now have an icon
 - Removed weird random lines on the borders of some icons

# Credits
 - Many thanks to bdew for figuring out how to use serverpacks functions in the sharedClassLoader context, as seen in this <a href="https://gist.github.com/bdew/5ee9fcb39da73405a38810116104e101">TestMod</a>
