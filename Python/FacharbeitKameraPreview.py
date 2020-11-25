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

# Preview ausführen
camera.start_preview()
sleep(10)
camera.stop_preview()
