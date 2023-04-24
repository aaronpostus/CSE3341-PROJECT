; eliminateNsort project - by Aaron Post
;
; There are no special considerations that I believe are necessary.
; I followed the lecture slide pseudocode for most of my design.
; The "join2" method implementation is inspired by @177 on Piazza.
; eliminates all elements in L1 that are also in L2
(define (elim L1 L2)
  (cond
    ((null? L1) ())
    ((null? L2) L1)
    ((xmembx (car L2) L1) (elim (cut1 (car L2) L1) (cdr L2)))
    (#t (elim L1 (cdr L2)))
  )
)
; returns list containing all elements except x
(define (cut1 x L)
  (cond
    ((null? L) L)
    ((= x (car L)) (cut1 x (cdr L)))
    (#t (cons (car L) (cut1 x (cdr L))))
   )
)
; returns length of list
(define (length L)
  (cond
    ((null? L) 0)
    (#t (+ 1 (length (cdr L))))
  )
)
; returns list that contains all elements less than x
(define (lesser x L)
  (cond
    ((null? L) ())
    ((> x (car L)) (cons (car L) (lesser x (cdr L))))
    (#t (lesser x (cdr L)))
  )
)
; returns list that contains all elements greater than or equal to x
(define (gEq x L)
  (cond
    ((null? L) ())
    ((<= x (car L)) (cons (car L) (gEq x (cdr L))))
    (#t (gEq x (cdr L)))
  )
)
; returns true if L1 contains x in the list anywhere
(define (xmembx x L)
  (cond
    ((null? L) #f)
    ((= x (car L)) #t)
    (#t (xmembx x (cdr L)))
  )
)
; sorts by non decreasing order
(define (quickSort L)
  (cond
    ((< (length L) 2) L)
    (#t (join1 (quickSort (lesser (car L) (cdr L))) (car L) (quickSort (gEq
    (car L) (cdr L)))))
  )
)
; joins a list, an element in the middle, and another list
(define (join1 L1 M L2)
  (cons (join2 L1 M) L2)
 )
; joins two lists together
(define (join2 L1 L2)
  (cond
    ((null? L1) L2)
    (#t (cons (car L1) (join2 (cdr L1) L2)))
  )
)
; eliminates all elements in L1 that are also in L2 and sorts the result
(define (eliminateNsort L1 L2)
  (cond
    ; nothing to eliminate or sort
    ((null? L1) ())
    ; nothing to eliminate
    ((null? L2) (quickSort(L1)))
    ; eliminate and sort
    (#t (quickSort (elim L1 L2)))
  )
)
