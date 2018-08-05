package com.ncuhome.QandA

import java.io.File
import java.io.FileFilter
import java.util.*

class utils {
	
	companion object {
		
		fun randInt(): Int {
			val r: Random = Random()
			return r.nextInt()
		}
		
		fun randIntRanged(range: Int): Int {
			val r: Random = Random()
			return r.nextInt(range)
		}
		
		fun shuffle(list: List<Any>) {
			Collections.shuffle(list)
		}
		
		fun listDir(directoryPath: String, suffix: String): MutableList<String> {
			val res: MutableList<String> = mutableListOf()
			val filePtr: File = File(directoryPath)
			val filter: FileFilter = FileFilter() {	it.isFile && it.name.endsWith(suffix) }
			if(!filePtr.exists()) {
				return res
			}
			val files = filePtr.listFiles(filter)
			for(file in files) {
				res.add(file.name)
			}
			return res
		}
		
		fun listDir(directoryPath: String):MutableList<String> {
			return listDir(directoryPath, "")
		}
	}
}
