

from PIL import Image, ImageDraw
import os

if not os.path.exists("drawn_numbers"):
    os.mkdir("drawn_numbers")

sheets = ["icons.png", "misc.png", "resource.png", "tools.png", "armor.png", "weapons.png", "resource2.png"]

sheetNum = 0

for sheet in sheets:
    image = Image.open("mods/Iconzz/resources/"+sheet)

    for i in range(sheetNum*240, (sheetNum+1)*240):
        draw = ImageDraw.Draw(image)
        left = (i%20)*32
        top = ((i%240)//20)*32
        draw.text((left+3, top+1), str(i), (247, 14, 193))
        
        cropped = image.crop((left, top, left+32, top+32))

        cropped.save("drawn_numbers/"+str(i), "png")
        cropped.close()
    image.close()


    sheetNum += 1