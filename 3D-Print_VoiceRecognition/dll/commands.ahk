#persistent

open(){
winactivate, Repetier-Host
send, ^o
}

connect(){
winactivate, Repetier-Host
controlclick, X39 Y75
}

slice(){
winactivate, Repetier-Host
controlclick, Slice mit CuraEngine
}

print(){
winactivate, Repetier-Host
controlclick, Drucken
}