/**
 * Copyright Sergey Olefir
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.solf.testngerrornotifier;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.kohsuke.MetaInfServices;
import org.slf4j.LoggerFactory;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Dumps error information right when it happens via System.err
 *
 * @author Sergey Olefir
 */
@MetaInfServices(ITestNGListener.class)
public class TestngErrorNotificationListener extends TestListenerAdapter
{

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onTestFailure(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailure(ITestResult tr)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		final String errMsg = "TESTNGFAIL: Error in test: " + tr;
		
		String newLine = "\n";
		if (errMsg.endsWith("\n") || errMsg.endsWith("\r"))
			newLine = "";
		final String sysErrMsg = "[" + Thread.currentThread() + "] " + new Date() + " " + errMsg + newLine;
		pw.print(sysErrMsg);
		if (tr.getThrowable() != null)
			tr.getThrowable().printStackTrace(pw);
		else
			pw.println("<no exception>");
		
		pw.flush();
		System.err.println(sw.toString());
		
		try
		{
			LoggerFactory.getLogger("testng-error-notifier").error(errMsg, tr.getThrowable());
		} catch (Throwable e)
		{
			// could be NoClassDefFoundError or w/e
		}
	}
	
}
