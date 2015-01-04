Opensource
==========

All my OpenSource utils and other useful stuff!

All my commits after 1-1-2015 were made for version 1.8 of Spigot!
I do not give support on lower versions!

Samples
==========

How to work with my utils in some easy steps:
**********
FlashUtil:

With FlashUtil you can make players flash red.

```java
//Flash a player manually
FlashUtil.getInstance().flashPlayer(player);

//Flash a player in a group
FlashUtil.getInstance().addFlashing(player);
FlashUtil.getInstance().flashPlayers();

//You can remove a player from flashing in the group using
FlashUtil.getInstance().removeFlashing(player);
```

**********
FakeHead:

With FakeHead you can give players fakeheads that are only visible for other players.

```java
//Set a player head
FakeHead.getInstance().setHead(player, new ItemStack(Material.DIAMOND_HELMET)); // You can use all valid hat items.

//The fakehead wont dissapear without restoring, the method will show up the actual hat that the player is wearing again!
//Restore it with
FakeHead.getInstance().restoreHead(player);
```


