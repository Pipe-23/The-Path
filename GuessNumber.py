import random
 
# def Guess(x):
#     random_number = random.randint(1,x)
#     guess = 0
#     while guess != random_number:
#         guess= int(input(f'Guess a number between 1 and {x}:  '))

#         if guess < random_number: {
#             print("Your guess is too low. Try it again")}
#         elif guess > random_number:{
#             print("Your guess is too high. Guess again")}

#     print (f"Congrats you made it! You have guessed the number {random_number} correctly! ")

# Guess(20)

def Computer_Number(x):
    low = 1
    high = x
    feedback = ""

    while feedback != 'c':
        guess= random.randint(low,high)
        feedback = input(f'Is {guess} too high (H), too low (L) or correct (C) ?').lower() 
    if feedback == 'h':
            high = guess - 1
    elif feedback == 'l':
            low = guess + 1
    
    print(f'Nice, the computer have guessed your number {guess} ')

Computer_Number(50)