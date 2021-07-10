package sample.member.app

//확장불가
sealed class Expr {
	class Num(val value: Int) : Expr()
	class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int =
		when (e) {
			is Expr.Num -> e.value
			is Expr.Sum -> eval(e.right) + eval(e.left)
		//else 예외케이스 불가
		}