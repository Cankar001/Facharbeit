import picamera
from time import sleep

# Kameraobjekt erstellen
camera = picamera.PiCamera()

# Kamerasettings
camera.framerate = 60
camera.exposure_mode = 'off'
camera.iso = 500
camera.shutter_speed = 6000000
camera.contrast = 0
camera.brightness = 50

# Foto schießen
camera.start_preview()
sleep(2.5)
camera.capture('../temp/bild.png', resize=(100,100))
camera.stop_preview()
